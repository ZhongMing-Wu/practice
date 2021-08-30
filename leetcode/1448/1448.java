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
    int count = 0;
    public int goodNodes(TreeNode root) {
        preOrder(root, Integer.MIN_VALUE);
        return count;
    }

    public void preOrder(TreeNode node, int curMax) {
        if(node == null) {
            return;
        }
        if(node.val >= curMax) {
            count++;
            curMax = node.val;
        }
        preOrder(node.left, curMax);
        preOrder(node.right, curMax);
    }
}