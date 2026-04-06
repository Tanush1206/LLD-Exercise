package DistributedCache.node;

import DistributedCache.storage.CacheStorage;

/**
 * Represents a single cache node
 */
public class CacheNode {

    private final String nodeId;
    private final CacheStorage storage;

    public CacheNode(String nodeId, CacheStorage storage) {
        this.nodeId = nodeId;
        this.storage = storage;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void put(String key, String value) {
        storage.put(key, value);
    }

    public String get(String key) {
        return storage.get(key);
    }

    public void delete(String key) {
        storage.delete(key);
    }
}
