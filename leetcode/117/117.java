/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/
class Solution {
    public Node connect(Node root) {
        Node first = root;
        while(first != null) {
            startLink(first);
            first = findFirst(first);
        }
        return root;
    }
    
    Node findFirst(Node first) {
        while(first != null) {
            if(first.left != null) {
                return first.left;
            }
            else if(first.right != null) {
                return first.right;
            }
            first = first.next;
        }
        return first;
    }
    
    void startLink(Node first) {
        Node node1 = findNextNodeHavingSonNode(first);
        Node node2 = null;
        if(node1 != null) {
            node2 = findNextNodeHavingSonNode(node1.next);
        }
        Node node1RightSon = null, node2LeftSon = null;
        while(node1 != null) {
            node1RightSon = linkSelf(node1, 0);
            node2LeftSon = linkSelf(node2, 1);
            node1RightSon.next = node2LeftSon;
            node1 = node2;
            if(node1 != null) {
                node2 = findNextNodeHavingSonNode(node1.next);
            }
        }
        
    }
    
    Node findNextNodeHavingSonNode(Node start) {
        while(start != null && start.left == null && start.right == null) {
            start = start.next;
        }
        return start;
    }
    
    Node linkSelf(Node node, int flag) {
        if(node == null) {
            return null;
        }
        if(node.left != null && node.right != null) {
            node.left.next = node.right;
            if(flag == 0) {
                return node.right;
            }
            else {
                return node.left;
            }
        }
        if(node.left != null) {
            return node.left;
        }
        if(node.right != null) {
            return node.right;
        }
        return null;
    }
}