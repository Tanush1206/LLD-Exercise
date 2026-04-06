package DistributedCache.eviction;

import DistributedCache.model.CacheEntry;
import java.util.concurrent.ConcurrentHashMap;


public interface EvictionPolicy {
    String evictKey(ConcurrentHashMap<String, CacheEntry> store);
}
