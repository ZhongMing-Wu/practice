/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        if(head == null) {
            return null;
        }
        Map<Node, Node> toNewNode = new HashMap<>();
        Node newHead = new Node(head.val);
        Node preNewNode = newHead;
        Node curOldNode = head.next, curNewNode = null;
        toNewNode.put(head, newHead);
        while(curOldNode != null) {
            curNewNode = new Node(curOldNode.val);
            preNewNode.next = curNewNode;
            preNewNode = curNewNode;
            toNewNode.put(curOldNode, curNewNode);
            curOldNode = curOldNode.next;
        }
        curOldNode = head;
        while(curOldNode != null) {
            curNewNode = toNewNode.get(curOldNode);
            if(curOldNode.random != null) {
                curNewNode.random = toNewNode.get(curOldNode.random);
            }
            curOldNode = curOldNode.next;
        }
        return newHead;
    }
}