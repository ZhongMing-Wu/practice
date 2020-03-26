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
    public List<Integer> postorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> ans = new ArrayList<>();
        TreeNode node = root, lastVisit = null;
        while(stack.empty() == false || node != null) {
            if(node != null) {
                stack.push(node);
                node = node.left;
            }
            else {
                node = stack.peek();
                if(node.right == null || node.right == lastVisit) {
                    ans.add(node.val);
                    lastVisit = node;
                    node = null;
                    stack.pop();
                }
                else {
                    node = node.right;
                }
            }
        }
        return ans;
    }
}