package cn.saintshaga.example.algorithm;

import java.util.List;

public class BTree {
    private int t;

    private BNode root;

    public BTree(int t) {
        if(t <= 0) {
            throw new IllegalArgumentException("error t");
        }
        this.t = t;
        root = null;
    }

    public Object search(int key) {
        return search(root, key);
    }

    private Object search(BNode node, int key) {
        if(node == null) {
            return null;
        }
        int i = 0;
        while(i < node.keys.size() && key > node.keys.get(i)) {
            i++;
        }
        if(i < node.keys.size() && key == node.keys.get(i)) {
            return node.values.get(i);
        }
        if(node.isLeaf) {
            return null;
        }
        return search(node.children.get(i), key);
    }

    public static class BNode {
        List<Integer> keys;
        List<Object> values;
        List<BNode> children;
        boolean isLeaf;
    }
}
