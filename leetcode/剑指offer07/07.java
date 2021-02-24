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
    Map<Integer, Integer> toIndex;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        toIndex = new HashMap<>(n);
        for(int i = 0; i < n; ++i) {
            toIndex.put(inorder[i], i);
        }

        return startBuildTree(preorder, inorder, 0, n - 1, 0, n - 1);
    }

    private TreeNode startBuildTree(int[] preorder, int[] inorder, int left1, int right1, int left2, int right2) {
        if(left1 > right1) {
            return null;
        }
        int index = toIndex.get(preorder[left1]);
        TreeNode node = new TreeNode(preorder[left1]);
        node.left = startBuildTree(preorder, inorder, left1 + 1, index - left2 + left1, left2, index - 1);
        node.right = startBuildTree(preorder, inorder, index - left2 + left1 + 1, right1, index + 1, right2);
        return node;
    }
}