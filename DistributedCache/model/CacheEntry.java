package DistributedCache.model;

public class CacheEntry {

    private final String key;
    private String value;
    private long lastAccessTime;

    public CacheEntry(String key, String value) {
        this.key = key;
        this.value = value;
        this.lastAccessTime = System.currentTimeMillis();
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        this.lastAccessTime = System.currentTimeMillis();
        return value;
    }

    public void setValue(String value) {
        this.value = value;
        this.lastAccessTime = System.currentTimeMillis();
    }

    public long getLastAccessTime() {
        return lastAccessTime;
    }
}
