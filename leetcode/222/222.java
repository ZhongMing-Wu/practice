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
    public int countNodes(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int left = calculateHeight(root.left);
        int right = calculateHeight(root.right);
        int totalCount = 0;
        if(left == right) {
            totalCount = countByHeight(left) + countNodes(root.right) + 1;
        }
        else {
            totalCount = countByHeight(right) + countNodes(root.left) + 1;
        }
        return totalCount;
    }
    public int calculateHeight(TreeNode node) {
        if(node == null) {
            return 0;
        }
        int left = calculateHeight(node.left);
        int right = calculateHeight(node.right);

        return 1 + Math.max(left, right);
    }
    public int countByHeight(int h) {
        if(h == 0) {
            return 0;
        }
        int count = 1;
        while(h != 0) {
            count = count << 1;
            h--;
        }
        return count - 1;
    }
}