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
    public int kthSmallest(TreeNode root, int k) {
        if(root == null) {
            return 0;
        }
        int visitCount = 0;
        Deque<TreeNode> stack = new ArrayDeque<>();

        TreeNode node = root;
        while(null != node || 0 != stack.size()) {
            if(node != null) {
                stack.addLast(node);
                node = node.left;
            } else {
                node = stack.pollLast();
                visitCount++;
                if(visitCount == k) {
                    return node.val;
                }
                node = node.right;
            }
        }
        return 0;
    }
}