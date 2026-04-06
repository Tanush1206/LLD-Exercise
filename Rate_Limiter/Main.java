package Rate_Limiter;

import Rate_Limiter.model.RateLimitConfig;
import Rate_Limiter.service.RateLimiter;
import Rate_Limiter.service.RateLimiterService;
import Rate_Limiter.storage.InMemoryStorage;
import Rate_Limiter.strategy.TokenBucketStrategy;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        RateLimitConfig config = new RateLimitConfig(5, 2);
        InMemoryStorage storage = new InMemoryStorage();

        TokenBucketStrategy strategy = new TokenBucketStrategy(config, storage);
        RateLimiter rateLimiter = new RateLimiter(strategy);

        RateLimiterService service = new RateLimiterService(rateLimiter);

        String client = "user-1";

        for (int i = 0; i < 10; i++) {
            service.processRequest(client);
            Thread.sleep(300);
        }
    }
}
