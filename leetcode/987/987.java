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
    class Node {
        public int y;  
        public int val;  

        public Node(int y, int val) {
            this.y = y;
            this.val = val;
        }
    }

    TreeMap<Integer, List<Node>> toNodeLists = new TreeMap<>();
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        preOrderTraversal(root, 0, 0);
        for(int key : toNodeLists.keySet()) {
            List<Node> list = toNodeLists.get(key);
            Collections.sort(list, (Node node1, Node node2) -> {
                if(node1.y != node2.y) {
                    return node2.y - node1.y;
                }
                return node1.val - node2.val;
            });
            List<Integer> oneAns = new ArrayList<>();
            for(Node node : list) {
                oneAns.add(node.val);
            }
            ans.add(oneAns);
        }
        return ans;
    }

    private void preOrderTraversal(TreeNode treeNode, int x, int y) {
        if(treeNode == null) {
            return;
        }
        Node node = new Node(y, treeNode.val);
        if(toNodeLists.containsKey(x)) {
            toNodeLists.get(x).add(node);
        } else {
            List<Node> list = new ArrayList<>();
            list.add(node);
            toNodeLists.put(x, list);
        }
        preOrderTraversal(treeNode.left, x - 1, y -1);
        preOrderTraversal(treeNode.right, x + 1, y - 1);
    }
}