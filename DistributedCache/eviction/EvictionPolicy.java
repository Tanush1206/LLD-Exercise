package DistributedCache.eviction;

import DistributedCache.model.CacheEntry;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Eviction strategy abstraction
 */
public interface EvictionPolicy {
    String evictKey(ConcurrentHashMap<String, CacheEntry> store);
}
