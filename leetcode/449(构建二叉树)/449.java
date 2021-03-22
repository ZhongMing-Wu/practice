/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = root, preNode = null;
        while(!stack.isEmpty() || node != null) {
            if(node != null) {
                stack.offerLast(node);
                node = node.left;
            } else {
                if(stack.peekLast().right != null && preNode != stack.peekLast().right) {
                    node = stack.peekLast().right;
                    stack.offerLast(node);
                    node = node.left;
                } else {
                    node = stack.pollLast();
                    sb.append(node.val + " ");
                    preNode = node;
                    node = null;
                }
            }
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.equals("")) {
            return null;
        }
        List<Integer> postOrder = new ArrayList<>();
        for(String s : data.split("\\s+")) {
            postOrder.add(Integer.valueOf(s));
        }
        List<Integer> inOrder = new ArrayList<>(postOrder);
        Collections.sort(inOrder);
        Map<Integer, Integer> toLoc = new HashMap<>();
        int n = inOrder.size();
        for(int i = 0; i < n; ++i) {
            toLoc.put(inOrder.get(i), i);
        }
        return doBuildTree(postOrder, inOrder, 0, n - 1, 0, n - 1, toLoc);
    }

    private TreeNode doBuildTree(List<Integer> postOrder, List<Integer> inOrder, int l1, int r1, int l2, int r2, Map<Integer, Integer> toLoc) {
        if(l1 > r1) {
            return null;
        }
        TreeNode node = new TreeNode(postOrder.get(r1));
        int loc = toLoc.get(node.val);
        node.left = doBuildTree(postOrder, inOrder, l1, l1 + loc - l2 - 1, l2,loc - 1, toLoc);
        node.right = doBuildTree(postOrder, inOrder, l1 + loc - l2, r1 - 1, loc + 1, r2, toLoc);
        return node;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// String tree = ser.serialize(root);
// TreeNode ans = deser.deserialize(tree);
// return ans;