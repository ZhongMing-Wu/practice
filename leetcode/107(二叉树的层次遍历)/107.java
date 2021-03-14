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
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null) {
            return ans;
        }
        List<Integer> partAns = null;
        int cur = 1, next = 0;
        Queue<TreeNode> bfsQueue = new LinkedList<>();
        bfsQueue.offer(root);
        while(!bfsQueue.isEmpty()) {
            partAns = new ArrayList<>(cur);
            TreeNode node = null;
            while(cur != 0) {
                node = bfsQueue.poll();
                --cur;
                partAns.add(node.val);
                if(node.left != null) {
                    bfsQueue.offer(node.left);
                    ++next;
                }
                if(node.right != null) {
                    bfsQueue.offer(node.right);
                    ++next;
                }
            }
            ans.add(partAns);
            cur = next;
            next = 0;
        }
        Collections.reverse(ans);
        return ans;
    }
}