package DistributedCache.eviction;

import DistributedCache.model.CacheEntry;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * LRU eviction policy
 */
public class LRUEvictionPolicy implements EvictionPolicy {

    @Override
    public String evictKey(ConcurrentHashMap<String, CacheEntry> store) {

        long oldestTime = Long.MAX_VALUE;
        String lruKey = null;

        for (Map.Entry<String, CacheEntry> entry : store.entrySet()) {
            if (entry.getValue().getLastAccessTime() < oldestTime) {
                oldestTime = entry.getValue().getLastAccessTime();
                lruKey = entry.getKey();
            }
        }

        return lruKey;
    }
}
