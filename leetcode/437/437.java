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
    public int pathSum(TreeNode root, int sum) {
        return startFind(root, sum, 0);
    }

    //flag = 1表示以目前节点为起始节点，0表示不以当前节点为起始节点
    private int startFind(TreeNode root, int sum, int flag) {
        if(root == null) {
            return 0;
        }
        int count = 0;
        if(flag == 1) {
            count += startFind(root.left, sum - root.val, 1);
            count += startFind(root.right, sum - root.val, 1);
        }
        else {
            count += startFind(root.left, sum - root.val, 1);
            count += startFind(root.right, sum - root.val, 1);

            count += startFind(root.left, sum, 0);
            count += startFind(root.right, sum, 0);
        }
        if(sum == root.val) {
            count++;
        }
        return count;
    }
}