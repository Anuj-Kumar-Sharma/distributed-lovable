const http = require('http');
const httpProxy = require('http-proxy');
const Redis = require('ioredis');

// Local fallback, but overridden by K8s env vars
const redisUrl = process.env.REDIS_URL || 'redis://localhost:6379';

const redis = new Redis(redisUrl, {
    maxRetriesPerRequest: null,
    enableReadyCheck: false,
    retryStrategy(times) {
        const delay = Math.min(times * 50, 2000);
        console.log(`Redis connection failed. Retrying in ${delay}ms...`);
        return delay;
    }
});

redis.on('error', (err) => console.error('Redis Client Error:', err.message));
redis.on('connect', () => console.log(`✅ Connected to Redis successfully at ${redisUrl}`));

const proxy = httpProxy.createProxyServer({
    ws: true,
    xfwd: true,
    changeOrigin: true
});

async function getTarget(hostname) {
    try {
        const targetIpOrSvc = await redis.get(`route:${hostname}`);
        return targetIpOrSvc || null;
    } catch (err) {
        console.error('Redis Error:', err);
        return null;
    }
}

// Automatically handles "localhost:5173" (local) or "preview-123-svc...:5173" (K8s)
const getTargetUrl = (target) => {
    return target.includes(':') ? `http://${target}` : `http://${target}:5173`;
};

const server = http.createServer(async (req, res) => {
    const rawHost = req.headers.host || ''; 
    const hostname = rawHost.split(':')[0];

    const targetIpOrSvc = await getTarget(hostname);

    if (!targetIpOrSvc) {
        res.writeHead(404, { 'Content-Type': 'text/plain' });
        return res.end(`Preview not found or spinning up for ${hostname}.`);
    }

    const target = getTargetUrl(targetIpOrSvc);
    console.log(`HTTP: ${hostname} -> ${target}${req.url}`);

    proxy.web(req, res, { target }, (e) => {
        console.error(`Proxy Error (Web): ${hostname} -> ${e.message}`);
        if (!res.headersSent) {
            res.writeHead(502);
            res.end('Preview server unavailable or starting...');
        }
    });
});

server.on('upgrade', async (req, socket, head) => {
    const rawHost = req.headers.host || '';
    const hostname = rawHost.split(':')[0];

    const targetIpOrSvc = await getTarget(hostname);

    if (targetIpOrSvc) {
        const target = getTargetUrl(targetIpOrSvc);
        console.log(`WS: ${hostname} -> ${target}`);

        proxy.ws(req, socket, head, { target }, (e) => {
            console.error(`Proxy Error (WS): ${hostname} -> ${e.message}`);
            socket.destroy();
        });
    } else {
        socket.destroy();
    }
});

const PORT = process.env.PORT || 80;
server.listen(PORT, () => console.log(`🚀 Wildcard Proxy Active on Port ${PORT}`));