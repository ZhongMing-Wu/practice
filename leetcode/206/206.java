/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        if(head == null)
            return null;
        ListNode preNode = head;
        ListNode currNode = preNode.next;
        ListNode nextNode;
        if(currNode != null) {
            nextNode = currNode.next;
        }
        else {
            return head;
        }

        while(nextNode != null) {
            currNode.next = preNode;
            preNode = currNode;
            currNode = nextNode;
            nextNode = nextNode.next;
        }
        currNode.next = preNode;
        head.next = null;
        return currNode;
    }
}