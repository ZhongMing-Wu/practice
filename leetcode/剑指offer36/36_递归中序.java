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
    Node lastVisitNode = null, head = null;
    public Node treeToDoublyList(Node root) {
        if(root == null) {
            return null;
        }
        inOrder(root);
        head.left = lastVisitNode;
        lastVisitNode.right = head;
        return head;
    }

    private void inOrder(Node node) {
        if(node == null) {
            return;
        }
        inOrder(node.left);
        node.left = lastVisitNode;
        if(lastVisitNode == null) {
            head = node;
        } else {
            lastVisitNode.right = node;
        }
        lastVisitNode = node;
        inOrder(node.right);
    }
}