FROM node:20-alpine
WORKDIR /app
COPY package.json ./
RUN npm install --production
COPY index.js ./
EXPOSE 80
CMD ["node", "index.js"]