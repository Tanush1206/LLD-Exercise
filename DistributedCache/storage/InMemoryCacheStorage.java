package DistributedCache.storage;

import DistributedCache.model.CacheEntry;
import DistributedCache.eviction.EvictionPolicy;

import java.util.concurrent.ConcurrentHashMap;

public class InMemoryCacheStorage implements CacheStorage {

    private final ConcurrentHashMap<String, CacheEntry> store;
    private final int capacity;
    private final EvictionPolicy evictionPolicy;

    public InMemoryCacheStorage(int capacity, EvictionPolicy evictionPolicy) {
        this.store = new ConcurrentHashMap<>();
        this.capacity = capacity;
        this.evictionPolicy = evictionPolicy;
    }

    @Override
    public synchronized void put(String key, String value) {
        if (store.size() >= capacity) {
            String evictKey = evictionPolicy.evictKey(store);
            if (evictKey != null) {
                store.remove(evictKey);
            }
        }

        store.put(key, new CacheEntry(key, value));
    }

    @Override
    public String get(String key) {
        CacheEntry entry = store.get(key);
        return entry != null ? entry.getValue() : null;
    }

    @Override
    public void delete(String key) {
        store.remove(key);
    }

    @Override
    public boolean contains(String key) {
        return store.containsKey(key);
    }
}
