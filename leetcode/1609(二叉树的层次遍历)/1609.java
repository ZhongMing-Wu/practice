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
    public boolean isEvenOddTree(TreeNode root) {
        if(root == null) {
            return true;
        }
        int curolevel = 0, cur = 1, next = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            TreeNode node = null;
            if(curolevel % 2 == 1) {
                int preVal = Integer.MAX_VALUE;
                while(cur != 0) {
                    node = queue.poll();
                    --cur;
                    if(node.val >= preVal || node.val % 2 == 1) {
                        return false;
                    }
                    if(node.left != null) {
                        queue.offer(node.left);
                        ++next;
                    }
                    if(node.right != null) {
                        queue.offer(node.right);
                        ++next;
                    }
                    preVal = node.val;
                }
            } else {
                int preVal = 0;
                while(cur != 0) {
                    node = queue.poll();
                    --cur;
                    if(node.val <= preVal || node.val % 2 == 0) {
                        return false;
                    }
                    if(node.left != null) {
                        queue.offer(node.left);
                        ++next;
                    }
                    if(node.right != null) {
                        queue.offer(node.right);
                        ++next;
                    }
                    preVal = node.val;
                }
            }
            cur = next;
            next = 0;
            ++curolevel;
        }
        return true;
    }   
}