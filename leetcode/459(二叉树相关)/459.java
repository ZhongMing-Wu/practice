/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        root = findAndDeleteNode(root, key);
        return root;
    }

    private TreeNode findAndDeleteNode(TreeNode node, int key) {
        if(node == null) {
            return null;
        }

        if(node.val > key) {
            node.left = findAndDeleteNode(node.left, key);
            return node;
        }

        if(node.val < key) {
            node.right = findAndDeleteNode(node.right, key);
            return node;
        }

        if(node.left == null && node.right == null) {
            return null;
        }

        if(node.left == null) {
            return node.right;
        }

        if(node.right == null) {
            return node.left;
        }

        TreeNode leftMaxNode = node.left, rightNeighborNode = node.right;
        while(leftMaxNode.right != null) {
            leftMaxNode = leftMaxNode.right;
        }
        leftMaxNode.right = rightNeighborNode.left;
        rightNeighborNode.left = node.left;
        return rightNeighborNode;
    }
}