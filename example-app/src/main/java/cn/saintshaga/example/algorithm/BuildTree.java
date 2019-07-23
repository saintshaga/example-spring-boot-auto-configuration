package cn.saintshaga.example.algorithm;

import java.util.HashMap;
import java.util.Map;

public class BuildTree {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> position = new HashMap<>(preorder.length);
        for(int i=0; i<inorder.length; i++) {
            position.put(inorder[i], i);
        }
        return buildTree(inorder, 0, inorder.length, preorder, 0, preorder.length, position);
    }

    private TreeNode buildTree(int[] inorder, int st, int ed, int[] preorder, int stp, int edp, Map<Integer, Integer> position) {
        if(st >= ed || stp >= edp || st < 0 || stp < 0 || ed > inorder.length || edp >= preorder.length) {
             return null;
        }
        int rootVal = preorder[stp];
        int indexInorder = position.get(rootVal);
        TreeNode root = new TreeNode(rootVal);
        root.left = buildTree(inorder, st, indexInorder, preorder, stp+1, stp+1+indexInorder-st, position);
        root.right = buildTree(inorder, indexInorder+1, ed, preorder, stp+1+indexInorder-st, stp-st+ed, position);
        return root;
    }

    public static void main(String[] args) {
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        TreeNode root = new BuildTree().buildTree(preorder, inorder);
    }

}
