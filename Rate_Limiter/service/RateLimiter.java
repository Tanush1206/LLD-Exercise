package Rate_Limiter.service;


import Rate_Limiter.strategy.RateLimitingStrategy;

public class RateLimiter {

    private final RateLimitingStrategy strategy;

    public RateLimiter(RateLimitingStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean allowRequest(String clientId) {
        return strategy.allowRequest(clientId);
    }
}
