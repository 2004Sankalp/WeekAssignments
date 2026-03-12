import java.util.*;

class DNSEntry {

    String ip;
    long expiryTime;

    DNSEntry(String ip, long ttl) {
        this.ip = ip;
        this.expiryTime = System.currentTimeMillis() + ttl;
    }
}

class DNSCache {

    private HashMap<String, DNSEntry> cache = new HashMap<>();

    private int hits = 0;
    private int misses = 0;

    public String resolve(String domain) {

        if(cache.containsKey(domain)) {

            DNSEntry entry = cache.get(domain);

            if(System.currentTimeMillis() < entry.expiryTime) {

                hits++;
                return "Cache HIT → " + entry.ip;
            }
        }

        misses++;

        String ip = queryUpstream(domain);

        cache.put(domain, new DNSEntry(ip, 300000));

        return "Cache MISS → " + ip;
    }

    private String queryUpstream(String domain) {

        return "192.168.1." + new Random().nextInt(255);
    }

    public void getCacheStats() {

        int total = hits + misses;

        double hitRate = (hits * 100.0) / total;

        System.out.println("Hit Rate: " + hitRate + "%");
    }
}