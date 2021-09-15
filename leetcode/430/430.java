/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/

class Solution {
    public Node flatten(Node head) {
        if(head == null) {
            return null;
        }
        Node[] children = flattenOneLevel(head);
        return children[0];
    }

    public Node[] flattenOneLevel(Node head) {
        Node curNode = head, nextNode = curNode.next, preNode = null;
        while(curNode != null) {
            Node[] children = null;
            if(curNode.child != null) {
                children = flattenOneLevel(curNode.child);
                curNode.next = children[0];
                children[0].prev = curNode;
                children[1].next = nextNode;
                if(nextNode != null) {
                    nextNode.prev = children[1];
                }
                curNode.child = null;
            }
            if(children != null) {
                preNode = children[1];
            } else {
                preNode = curNode;
            }
            curNode = nextNode;
            if(nextNode != null) {
                nextNode = nextNode.next;
            }
        }
        return new Node[] {head, preNode};
    }
}