package cn.saintshaga.example.cache;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
class LFUCache {
    private int capacity;
    private Map<Integer, Node> map;
    private NodeChain head;
    private NodeChain tail;


    public LFUCache(int capacity) {
        if(capacity <= 0) {
            throw new IllegalArgumentException("capacity should be positive!");
        }
        this.capacity = capacity;
        this.map = new HashMap<>(capacity);
        this.head = createNodeChain(-1);
        this.tail = createNodeChain(0);
        head.next = tail;
        head.prev = null;
        tail.next = null;
        tail.prev = head;
    }

    public int get(int key) {
        if(!map.containsKey(key)) {
            return -1;
        }
        Node node = map.get(key);
        nodeCountPlus(node);
        return node.value;
    }

    public void put(int key, int value) {
        if(map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            nodeCountPlus(node);
            return;
        }
        if(map.size() == capacity) {
            removeLastFrequencyNode();
        }
        Node node = createNode(tail, key, value);
        if(tail.prev.count != 1) {
            insertNewNodeChainBefore(1, tail);
        }
        insertNodeToHeadOfChain(node, tail.prev);
        map.put(key, node);
    }

    private void removeLastFrequencyNode() {
        NodeChain chain = tail.prev;
        Node node = chain.tail.prev;
        breakDownNode(node);
        map.remove(node.key);
        if(chain.isEmpty()) {
            removeChain(chain);
        }
    }

    private void removeChain(NodeChain chain) {
        chain.prev.next = chain.next;
        chain.next.prev = chain.prev;
        chain.prev = null;
        chain.next = null;
    }

    private void nodeCountPlus(Node node) {
        NodeChain chain = node.parent;
        if(chain.prev.count != chain.count + 1) {
            insertNewNodeChainBefore(chain.count+1, chain);
        }
        breakDownNode(node);
        insertNodeToHeadOfChain(node, chain.prev);
        if(chain.isEmpty()) {
            removeChain(chain);
        }
    }

    private void insertNodeToHeadOfChain(Node node, NodeChain chain) {
        Node temp = chain.head.next;
        chain.head.next = node;
        node.next = temp;
        temp.prev = node;
        node.prev = chain.head;
        node.accessTimestamp = System.currentTimeMillis();
        node.parent = chain;
    }

    private void breakDownNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.next = null;
        node.prev = null;
        node.parent = null;
    }

    private void insertNewNodeChainBefore(int count, NodeChain currentChain) {
        NodeChain chain = createNodeChain(count);
        NodeChain temp = currentChain.prev;
        temp.next = chain;
        chain.next = currentChain;
        currentChain.prev = chain;
        chain.prev = temp;
    }

    private NodeChain createNodeChain(int count) {
        NodeChain chain = new NodeChain();
        chain.head = createNode(chain, -1, -1);
        chain.tail = createNode(chain, -1, -1);
        chain.prev = null;
        chain.next = null;
        chain.head.next = chain.tail;
        chain.tail.prev = chain.head;
        chain.count = count;
        return chain;
    }

    private Node createNode(NodeChain parent, int key, int value) {
        Node node = new Node();
        node.key = key;
        node.value = value;
        node.parent = parent;
        node.prev = null;
        node.next = null;
        node.accessTimestamp = System.currentTimeMillis();
        return node;
    }

    public static class Node {
        int key;
        int value;
        long accessTimestamp;
        Node prev;
        Node next;
        NodeChain parent;
    }

    public static class NodeChain {
        Node head;
        Node tail;
        NodeChain prev;
        NodeChain next;
        int count;

        public boolean isEmpty() {
            return head.next == tail;
        }
    }

    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2);
        cache.put(1,1);
        cache.put(2,2);
        log.info("{}", cache.get(1));
        cache.put(3,3);
        log.info("{}", cache.get(2));
        log.info("{}", cache.get(3));
        cache.put(4,4);
        log.info("{}", cache.get(1));
        log.info("{}", cache.get(3));
        log.info("{}", cache.get(4));

        log.info("{}", "a" + "b" == "ab");

    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
