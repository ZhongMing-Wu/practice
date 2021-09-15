/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _next) {
        val = _val;
        next = _next;
    }
};
*/

class Solution {
    public Node insert(Node head, int insertVal) {
        if(head == null) {
            head = new Node(insertVal);
            head.next = head;
            return head;
        }
        Node node = head;
        Node newNode = new Node(insertVal);
        while(true) {
            if(node.next == head) {
                newNode.next = node.next;
                node.next = newNode;
                break;
            }
            if(node.val == insertVal) {
                newNode.next = node.next;
                node.next = newNode;
                break;
            }
            if(node.val < insertVal && node.next.val > insertVal) {
                newNode.next = node.next;
                node.next = newNode;
                break;
            }
            if(node.val < insertVal && node.next.val < insertVal && node.val > node.next.val) {
                newNode.next = node.next;
                node.next = newNode;
                break;
            }

            if(node.val > insertVal && node.next.val > insertVal && node.val > node.next.val) {
                newNode.next = node.next;
                node.next = newNode;
                break;
            }
            node = node.next;
        } 
        return head;
    }
}