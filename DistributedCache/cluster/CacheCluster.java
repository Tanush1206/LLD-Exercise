package DistributedCache.cluster;

import DistributedCache.node.CacheNode;

public class CacheCluster {

    private final ConsistentHashing hashing;

    public CacheCluster(ConsistentHashing hashing) {
        this.hashing = hashing;
    }

    public void addNode(CacheNode node) {
        hashing.addNode(node);
    }

    public void removeNode(CacheNode node) {
        hashing.removeNode(node);
    }

    public CacheNode getNode(String key) {
        return hashing.getNode(key);
    }
}
