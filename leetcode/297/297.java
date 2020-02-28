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
        StringBuilder ans = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode tempNode;
        while(queue.isEmpty() == false) {
            tempNode = queue.peek();
            if(tempNode != null) {
                queue.offer(tempNode.left);
                queue.offer(tempNode.right);
                ans.append(tempNode.val);
                ans.append('.');
            }
            else {
                ans.append("null.");
            }
            queue.poll();
        }
        return ans.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.equals("null")) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        int i = 0, j = findNextPoint(data, i);
        TreeNode root = new TreeNode(Integer.parseInt(data.substring(i, j)));
        queue.offer(root);
        TreeNode tempNode;
        i = j + 1;
        String leftVal, rightVal;
        while(i < data.length()) {
            tempNode = queue.peek();
            if(tempNode != null) {
                j = findNextPoint(data, i); 
                leftVal = data.substring(i, j);
                i = j + 1;
                j = findNextPoint(data, i);
                rightVal = data.substring(i, j);
                i = j + 1;
                if(leftVal.equals("null")) {
                    if(!rightVal.equals("null")) {
                        tempNode.right = new TreeNode(Integer.parseInt(rightVal));
                    }
                }
                else {
                    if(rightVal.equals("null")) {
                        tempNode.left = new TreeNode(Integer.parseInt(leftVal));
                    }
                    else {
                        tempNode.left = new TreeNode(Integer.parseInt(leftVal));
                        tempNode.right = new TreeNode(Integer.parseInt(rightVal));
                    }
                }
                queue.offer(tempNode.left);
                queue.offer(tempNode.right);
            }
            queue.poll();
        }
        return root;
    }

    int findNextPoint(String str, int start) {
        while(str.charAt(start) != '.') {
            start++;
        }
        return start;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));