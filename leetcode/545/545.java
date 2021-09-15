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
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if(root == null) {
            return ans;
        }

        if(root.left == null && root.right == null) {
            ans.add(root.val);
            return ans;
        }
        List<Integer> leftBoundary = null;
        if(root.left != null) {
            leftBoundary = findLeftBoundary(root.left);
        }

        List<Integer> rightBoundary = null;
        if(root.right != null) {
            rightBoundary = findRightBoundary(root.right);
        }

        List<Integer> leafNodesVal = findLeafNodesVal(root);
        ans.add(root.val);
        if(leftBoundary != null) {
            ans.addAll(leftBoundary);
        }
        ans.addAll(leafNodesVal);
        if(rightBoundary != null) {
            for(int i = rightBoundary.size() - 1; i >= 0; i--) {
                ans.add(rightBoundary.get(i));
            }
        }
        return ans;
    }

    public List<Integer> findLeftBoundary(TreeNode node) {
        List<Integer> leftBoundary = new ArrayList<>();
        while(node.left != null || node.right != null) {
            leftBoundary.add(node.val);
            if(node.left != null) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return leftBoundary;
    }

    public List<Integer> findRightBoundary(TreeNode node) {
        List<Integer> rightBoundary = new ArrayList<>();
        while(node.left != null || node.right != null) {
            rightBoundary.add(node.val);
            if(node.right != null) {
                node = node.right;
            } else {
                node = node.left;
            }
        }

        return rightBoundary;
    }

    public List<Integer> findLeafNodesVal(TreeNode node) {
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode p = node;
        List<Integer> leafNodesVal = new ArrayList<>();
        while(!stack.isEmpty() || p != null) {
            if(p != null) {
                if(p.left == null && p.right == null) {
                    leafNodesVal.add(p.val);
                }
                stack.offerLast(p);
                p = p.left;
                continue;
            }
            p = stack.pollLast();
            p = p.right;
        }
        return leafNodesVal;
    }
}