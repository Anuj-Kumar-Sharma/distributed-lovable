package com.codingshuttle.distributed_lovable.intelligence_service.service;

public interface UsageService {
    void recordTokenUsage(Long userId, int actualTokens);
    void checkDailyTokensUsage();
}
