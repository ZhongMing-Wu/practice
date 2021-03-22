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
    public int maxPathSum(TreeNode root) {
        ans = Integer.MIN_VALUE;
        dfs(root);
        return ans;
    }
    private int[] dfs(TreeNode node) {
        if(node == null) {
            return new int[] {0, 0};
        }

        int[] left = dfs(node.left);
        int[] right = dfs(node.right);

        int leftMax = Math.max(left[0], left[1]);
        int rightMax = Math.max(right[0], right[1]);

        int[] dp = new int[2];
        dp[0] = leftMax > 0 ? leftMax + node.val : node.val;
        dp[1] = rightMax > 0 ? rightMax + node.val : node.val;

        ans = Math.max(ans, dp[0] + dp[1] - node.val);
        return dp;
    }
}