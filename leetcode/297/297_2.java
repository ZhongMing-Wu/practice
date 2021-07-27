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
        if(root == null) {
            return "null";
        }     
        TreeNode node = root;
        Deque<TreeNode> stack = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty() || node != null) {
            if(node != null) {
                stack.offerLast(node);
                sb.append(node.val).append(',');
                node = node.left;
            } else {
                sb.append("null,");
                node = stack.pollLast();
                node = node.right;
            }
        }
        sb.append("null,");
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    int curLoc;
    public TreeNode deserialize(String data) {
        if(data.equals("null")) {
            return null;
        }     
        curLoc = 0;
        return buildChildNode(data);
    }
    private TreeNode buildChildNode(String data) {
        int end = findNextStart(data, curLoc);
        if(curLoc == end) {
            return null;
        }
        String partData = data.substring(curLoc, end);
        curLoc = end + 1;
        if(partData.equals("null") || partData.length() == 0) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.valueOf(partData));
        node.left = buildChildNode(data);
        node.right = buildChildNode(data);
        return node;
    }
    private int findNextStart(String data, int loc) {
        while(loc < data.length() && data.charAt(loc) != ',') {
            ++loc;
        }
        return loc;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));