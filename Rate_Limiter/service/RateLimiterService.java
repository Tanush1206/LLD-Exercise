package Rate_Limiter.service;

public class RateLimiterService {

    private final RateLimiter rateLimiter;

    public RateLimiterService(RateLimiter rateLimiter) {
        this.rateLimiter = rateLimiter;
    }

    public void processRequest(String clientId) {
        if (rateLimiter.allowRequest(clientId)) {
            System.out.println("Request allowed for: " + clientId);
        } else {
            System.out.println("Rate limit exceeded for: " + clientId);
        }
    }
}
