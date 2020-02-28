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
    public TreeNode convertBST(TreeNode root) {
        tackleNode(root, 0);
        return root;
    }

    //parentSum记录当前节点父节点改变后的值
    int tackleNode(TreeNode root, int parentVal) {
        if(root == null) {
            return 0;
        }
        int returnVal = root.val;
        int rightVal = tackleNode(root.right, parentVal);
        root.val = root.val + rightVal + parentVal;
        int leftVal = tackleNode(root.left, root.val);
        returnVal += leftVal + rightVal;
        return returnVal;
    }
}