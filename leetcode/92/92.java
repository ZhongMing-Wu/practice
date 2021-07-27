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
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(left == right) {
            return head;
        }
        ListNode preHead = new ListNode();
        preHead.next = head;
        ListNode leftNode = preHead, rightNode, preLeftNode, pre, nextRightNode = null;
        int nextLoc = 1;
        while(nextLoc < left) {
            leftNode = leftNode.next;
            ++nextLoc;
        }
        preLeftNode = leftNode;
        leftNode = leftNode.next;
        pre = leftNode;
        rightNode = leftNode.next;
        int curLoc = nextLoc + 1;
        while(curLoc <= right) {
            nextRightNode = rightNode.next;
            rightNode.next = pre;
            pre = rightNode;
            rightNode = nextRightNode;
            ++curLoc;
        }
        preLeftNode.next = pre;
        leftNode.next = rightNode;
        return preHead.next;
    }
}