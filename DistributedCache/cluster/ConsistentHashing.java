package DistributedCache.cluster;

import DistributedCache.node.CacheNode;

import java.util.SortedMap;
import java.util.TreeMap;

public class ConsistentHashing {

    private final SortedMap<Integer, CacheNode> ring = new TreeMap<>();

    public void addNode(CacheNode node) {
        int hash = getHash(node.getNodeId());
        ring.put(hash, node);
    }

    public void removeNode(CacheNode node) {
        ring.remove(getHash(node.getNodeId()));
    }

    public CacheNode getNode(String key) {
        if (ring.isEmpty()) return null;

        int hash = getHash(key);

        if (!ring.containsKey(hash)) {
            SortedMap<Integer, CacheNode> tailMap = ring.tailMap(hash);
            hash = tailMap.isEmpty() ? ring.firstKey() : tailMap.firstKey();
        }

        return ring.get(hash);
    }

    private int getHash(String key) {
        return key.hashCode();
    }
}
