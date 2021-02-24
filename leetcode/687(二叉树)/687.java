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
    int ans;
    public int longestUnivaluePath(TreeNode root) {
        ans = 0;
        dfs(root, -1);
        return ans;
    }
    
    private int dfs(TreeNode node, int expectVal) {
        if(node == null) {
            return 0;
        }

        int leftCount, rightCount;
        
        leftCount = dfs(node.left, node.val);
        rightCount = dfs(node.right, node.val);
        ans = Math.max(ans, leftCount + rightCount);
        
        if(node.val == expectVal) {
            return Math.max(leftCount, rightCount) + 1;
        }
        return 0;
    }
}