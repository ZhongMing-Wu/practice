/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode headList2 = list2, tailList2 = list2;
        while(tailList2.next != null) {
            tailList2 = tailList2.next;
        }

        ListNode preANode = null, aNode = list1, nextBNode = list1;
        while(a != 0) {
            preANode = aNode;
            aNode = aNode.next;
            nextBNode = nextBNode.next;
            --a;
            --b;
        }

        while(b != 0) {
            nextBNode = nextBNode.next;
            --b;
        }
        ListNode temp = nextBNode;
        nextBNode = nextBNode.next;
        temp.next = null;

        ListNode head = null;
        if(preANode != null) {
            preANode.next = headList2;
            head = list1;
        } else {
            head = headList2;
        }

        tailList2.next = nextBNode;

        return head;
    }
}