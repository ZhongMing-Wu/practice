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
    List<Integer> nodes = null;
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        nodes = new ArrayList<>();
        findTarget(root, target.val, 0, k);
        return nodes;
    }

    public int findTarget(TreeNode node, int target, int level, int k) {
        if(node == null) {
            return -1;
        }

        if(node.val == target) {
            findKDistanceNode(node, k);
            return level;
        }
        int left = findTarget(node.left, target, level + 1, k);
        int right = findTarget(node.right, target, level + 1, k);

        if(left != -1) {
            if(left - level == k) {
                nodes.add(node.val);
            }
            if(left - level < k && node.right != null) {
                findKDistanceNode(node.right, k - left + level - 1);
            }
        }

        if(right != -1) {
            if(right - level == k) {
                nodes.add(node.val);
            }
            if(right - level < k && node.left != null) {
                findKDistanceNode(node.left, k - right + level - 1);
            }
        }
        return right == -1 ? left : right;
    }

    public void findKDistanceNode(TreeNode node, int k) {
        if(k == 0) {
            nodes.add(node.val);
            return;
        }
        if(node.left != null) {
            findKDistanceNode(node.left, k - 1);
        }
        if(node.right != null) {
            findKDistanceNode(node.right, k - 1);
        }
    }
}