package Rate_Limiter.model;


public class TokenBucket {

    private final int capacity;
    private final int refillRate;

    private double tokens;
    private long lastRefillTimestamp;

    public TokenBucket(int capacity, int refillRate) {
        this.capacity = capacity;
        this.refillRate = refillRate;
        this.tokens = capacity;
        this.lastRefillTimestamp = System.nanoTime();
    }

    public synchronized boolean tryConsume(int tokensRequested) {
        refill();

        if (tokens >= tokensRequested) {
            tokens -= tokensRequested;
            return true;
        }
        return false;
    }

    private void refill() {
        long now = System.nanoTime();
        double elapsedSeconds = (now - lastRefillTimestamp) / 1e9;

        double tokensToAdd = elapsedSeconds * refillRate;
        tokens = Math.min(capacity, tokens + tokensToAdd);

        lastRefillTimestamp = now;
    }
}
