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
    public List<List<Integer>> verticalOrder(TreeNode root) {
        if(root == null) {
            return new ArrayList<>();
        }
        HashMap<Integer, List<Integer>> toColumnVals = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        int leftLimit = 0, rightLimit = 0;
        queue.offer(new Node(root.val, 0, root));
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            int column = node.column;
            if(!toColumnVals.containsKey(column)) {
                toColumnVals.put(column, new ArrayList<>());
            }
            List<Integer> columnVals = toColumnVals.get(column);
            columnVals.add(node.val);
            if(node.treeNode.left != null) {
                queue.offer(new Node(node.treeNode.left.val, column - 1, node.treeNode.left));
                leftLimit = Math.min(leftLimit, column - 1);
            }
            if(node.treeNode.right != null) {
                queue.offer(new Node(node.treeNode.right.val, column + 1, node.treeNode.right));
                rightLimit = Math.max(rightLimit, column + 1);
            }
        }
        List<List<Integer>> ans = new ArrayList<>();
        for(int i = leftLimit; i <= rightLimit; i++) {
            ans.add(toColumnVals.get(i));
        }
        return ans;
    }
}

class Node {
    int val;
    int column;
    TreeNode treeNode;
    public Node(int val, int column, TreeNode treeNode) {
        this.val = val;
        this.column = column;
        this.treeNode = treeNode;
    }
}