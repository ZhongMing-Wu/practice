/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int rob(TreeNode root) {
        int[] ans = startFind(root);
        return Math.max(ans[0], ans[1]);
    }

    int[] startFind(TreeNode root) {
        int[] dp = new int[2];  //dp[0]存放rob该节点能够盗取的最大金额，dp[1]存放不rob该节点能够盗取的最大金额
        if(root == null) {
            return dp;
        }

        int[] left = startFind(root.left);
        int[] right = startFind(root.right);

        dp[0] = root.val + left[1] + right[1];
        dp[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        return dp;
    }

}