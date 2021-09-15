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
    double maxAverage = 0.00000;
    public double maximumAverageSubtree(TreeNode root) {
        calculateAverage(root);
        return maxAverage;
    }

    public int[] calculateAverage(TreeNode node) {
        if(node == null) {
            return new int[] {0, 0};
        }
        int[] left = calculateAverage(node.left);
        int[] right = calculateAverage(node.right);

        int sum = node.val + left[0] + right[0];
        double average = 1.0 * sum / (left[1] + right[1] + 1);
        average = (int)(average * 100000) / 100000.0;
        maxAverage = Math.max(maxAverage, average);
        return new int[] {sum, left[1] + right[1] + 1};
    }
}