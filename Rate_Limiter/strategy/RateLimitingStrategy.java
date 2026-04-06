package Rate_Limiter.strategy;

public interface RateLimitingStrategy {
    boolean allowRequest(String clientId);
}
