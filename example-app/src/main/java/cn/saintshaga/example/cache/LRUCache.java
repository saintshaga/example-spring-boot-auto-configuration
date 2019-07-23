package cn.saintshaga.example.cache;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

public class LRUCache<V> {
    @Data
    public static class Node<V> {
        private V value;
        private Node<V> next, prev;
    }

    private Map<String, Node<V>> map;
    private int size;
    private Node<V> head, tail;

    public LRUCache(int size) {
        if(size <= 0) {
            throw new IllegalArgumentException("size should be positive");
        }
        this.size = size;
        this.map = new HashMap<>(size);
        this.head = new Node<>();
        this.tail = new Node<>();
        head.next = tail;
        tail.prev = head;
    }

    public void put(String key, V v) {
        if(map.containsKey(key)) {
            Node<V> node = map.get(key);
            node.setValue(v);
            moveToHead(node);
            return;
        }
        if(map.size() >= size) {
            removeLast();
        }
        Node<V> node = new Node<>();
        node.setValue(v);
        insertToHead(node);
    }

    public V get(String key) {
        if(!map.containsKey(key)) {
            return null;
        }
        Node<V> node = map.get(key);
        moveToHead(node);
        return node.getValue();
    }

    private void moveToHead(Node<V> node) {
        if(node == head.next) {
            return;
        }
        Node<V> temp = node.prev;
        temp.next = node.next;
        node.next.prev = temp;
        insertToHead(node);
    }

    private void insertToHead(Node<V> node) {
        Node<V> temp = head.next;
        head.next = node;
        node.prev = head;
        temp.prev = node;
        node.next = temp;
    }

    private void removeLast() {
        if(head.next == tail || tail.prev == head) {
            return;
        }
        Node<V> removed = tail.prev;
        removed.prev.next = tail;
        tail.prev = removed.prev;

        removed.next = null;
        removed.prev = null;
    }

}
