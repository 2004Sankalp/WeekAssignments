import java.util.HashMap;

class TokenBucket {

    int tokens;
    int maxTokens;
    long lastRefillTime;
    int refillRate;

    public TokenBucket(int maxTokens, int refillRate) {
        this.tokens = maxTokens;
        this.maxTokens = maxTokens;
        this.refillRate = refillRate;
        this.lastRefillTime = System.currentTimeMillis();
    }

    public synchronized boolean allowRequest() {

        refill();

        if(tokens > 0) {
            tokens--;
            return true;
        }

        return false;
    }

    private void refill() {

        long now = System.currentTimeMillis();
        long elapsed = now - lastRefillTime;

        int tokensToAdd = (int)(elapsed / 3600000.0 * refillRate);

        if(tokensToAdd > 0) {

            tokens = Math.min(maxTokens, tokens + tokensToAdd);
            lastRefillTime = now;
        }
    }
}

public class Problem6_RateLimiter {

    static HashMap<String, TokenBucket> clients = new HashMap<>();

    public static String checkRateLimit(String clientId) {

        clients.putIfAbsent(clientId, new TokenBucket(1000,1000));

        TokenBucket bucket = clients.get(clientId);

        if(bucket.allowRequest())
            return "Allowed (" + bucket.tokens + " remaining)";
        else
            return "Denied (Rate limit exceeded)";
    }

    public static void main(String[] args) {

        System.out.println(checkRateLimit("abc123"));
    }
}