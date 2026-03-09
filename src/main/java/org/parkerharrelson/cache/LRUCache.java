package org.parkerharrelson.cache;

import java.util.HashMap;

import org.parkerharrelson.linkedlist.LinkedList;
import org.parkerharrelson.linkedlist.Node;

public class LRUCache<E, T> {

    private final HashMap<E, Node<E, T>> cache;
    private final LinkedList<E, T> linkedList;
    private final int maxSize;

    public LRUCache() {
        maxSize = 1_000;
        cache = new HashMap<>();
        linkedList = new LinkedList<>();
    }

    public LRUCache(int maxSize) {
        if (maxSize < 1) {
            throw new IllegalArgumentException("maxSize must be at least 1");
        }
        this.maxSize = maxSize;
        cache = new HashMap<>();
        linkedList = new LinkedList<>();
    }

    // Put Item
    public void put(E key, T value) {
        if (maxSize <= 0) {
            return;
        }

        if (cache.containsKey(key)) {
            Node<E, T> existing = cache.get(key);
            existing.setData(value);
            linkedList.removeNode(existing);
            linkedList.insertHead(existing);
            return;
        }

        if (linkedList.getSize() >= maxSize) {
            Node<E, T> curLast = linkedList.peekLastAndRemove();
            cache.remove(curLast.getKey());
        }

        Node<E, T> node = new Node<>(key, value);
        linkedList.insertHead(node);
        cache.put(key, node);
    }

    // Get Item
    public T get(E key) {
        if (key == null) {
            return null;
        }

        Node<E, T> node = cache.get(key);
        if (node == null) {
            return null;
        }

        linkedList.removeNode(node);
        linkedList.insertHead(node);
        return node.getData();
    }

    // Evict Item
    public boolean evict(E key) {
        if (key == null) {
            return false;
        }

        Node<E, T> removedNode = cache.remove(key);

        if (removedNode != null) {
            linkedList.removeNode(removedNode);
            return true;
        }

        return false;
    }

    // Clear Cache
    public void clear() {
        cache.clear();
        linkedList.clear();
    }

    // Get Current Size
    public int getCurrentSize() {
        return linkedList.getSize();
    }

    public void printInOrder() {
        Node<E, T> cur = linkedList.peekFirst();

        if (cur == null) {
            return;
        }

        while (cur != null) {
            System.out.println(cur.getKey() + " : " + cur.getData());
            cur = cur.getNext();
        }
    }

}
