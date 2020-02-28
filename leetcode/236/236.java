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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        ans = null;
        findNode(root, p, q);
        return ans;
    }

    boolean findNode(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) {
            return false;
        }
        if(ans != null) {
            return true;
        }

        boolean left = findNode(root.left, p, q);
        boolean right = findNode(root.right, p, q);

        if(ans != null) {
            return true;
        }

        if(left == true && right == true) {
            ans = root;
            return true;
        }
        else if(left == true || right == true) {
            if(root == p || root == q) {
                ans = root;
                return true;
            }
            else {
                return true;
            }
        }
        else {
            if(root == p || root == q) {
                return true;
            }
            else {
                return false;
            }
        }
    }
    private TreeNode ans;
}