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
    List<List<Integer>> ans;
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        ans = new ArrayList<>();
        if(root == null) {
            return ans;
        }
        LinkedList<Integer> oneAns = new LinkedList<>();
        doPathSum(root, oneAns, 0, target);
        return ans;
    }

    private void doPathSum(TreeNode node, LinkedList<Integer> oneAns, int curSum, int target) {
        curSum += node.val;
        oneAns.offerLast(node.val);
        if(node.left == null && node.right == null) {
            if(curSum == target) {
                doAddList(oneAns);
            }
            oneAns.pollLast();
            return;
        }
        if(node.left != null) {
            doPathSum(node.left, oneAns, curSum, target);   
        }
        if(node.right != null) {
            doPathSum(node.right, oneAns, curSum, target);   
        }
        oneAns.pollLast();
    }

    private void doAddList(LinkedList<Integer> oneAns) {
        List<Integer> newAns = new ArrayList<>(oneAns);
        ans.add(newAns);
    }
}