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
            return null;
        }
        Deque<Node> stack = new LinkedList<>();
        Node head = null, preNode = null, p = root;
        while(!stack.isEmpty() || p != null) {
            if(p != null) {
                stack.offerLast(p);
                p = p.left;
                continue;
            }
            p = stack.pollLast();
            if(head == null) {
                head = p;
            }
            p.left = preNode;
            if(preNode != null) {
                preNode.right = p;
            }
            preNode = p;
            p = p.right;
        }  
        head.left = preNode;
        preNode.right = head;
        return head;  
    }
}