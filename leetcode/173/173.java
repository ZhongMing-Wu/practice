/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class BSTIterator {
    private Stack<TreeNode> stack;
    public BSTIterator(TreeNode root) {
        stack = new Stack<TreeNode>();
        findNextNodes(root);
    }
    
    /** @return the next smallest number */
    public int next() {
        TreeNode curNode = stack.pop();
        findNextNodes(curNode.right);
        return curNode.val;
    }
    
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        if(stack.isEmpty()) {
            return false;
        }
        else {
            return true;
        }
    }

    void findNextNodes(TreeNode root) {
        if(root == null) {
            return;
        }
        TreeNode node = root;
        while(node != null) {
            stack.push(node);
            node = node.left;
        }
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */