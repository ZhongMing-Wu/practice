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
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if(root == null || p == null) {
            return null;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        int preVal = p.val + 1;
        TreeNode node = root;
        while(!stack.isEmpty() || node != null) {
            if(node != null) {
                stack.offerLast(node);
                node = node.left;
                continue;
            }

            node = stack.pollLast();
            if(preVal == p.val) {
                return node;
            }
            preVal = node.val;
            node = node.right;
        }
        return null;
    }
}