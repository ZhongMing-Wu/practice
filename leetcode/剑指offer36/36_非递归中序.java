/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/
class Solution {
    public Node treeToDoublyList(Node root) {
        if(root == null) {
            return root;
        }
        Node node = root, lastVistNode = null, head = null;
        Deque<Node> stack = new LinkedList<>();
        while(node != null || !stack.isEmpty()) {
            if(node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                node.left = lastVistNode;
                if(lastVistNode == null) {
                    head = node;
                } else {
                    lastVistNode.right = node;
                }
                lastVistNode = node;
                node = node.right;
            }
        }  
        head.left = lastVistNode;
        lastVistNode.right = head;
        return head;
    }
}