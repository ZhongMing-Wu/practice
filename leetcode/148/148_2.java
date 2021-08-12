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
    public ListNode sortList(ListNode head) {
        ListNode preHead = new ListNode(), pre = null;
        ListNode curNode = head;
        int len = 0;
        while(curNode != null) {
            len++;
            curNode = curNode.next;
        }
        preHead.next = head;
        for(int i = 1; i < len * 2; i = i << 1) {
            pre = preHead;
            head = pre.next;
            while(pre != null) {
                pre = merge(pre, head, i);
                if(pre != null) {
                    head = pre.next;
                }
            }
        }
        return preHead.next;
    }

    private ListNode merge(ListNode pre, ListNode head, int sortCount) {
        if(sortCount == 1) {
            pre.next = head;
            pre = head;
            return pre;
        }
        ListNode secondCurNode = findNextHead(head, sortCount / 2);
        if(secondCurNode == null) {
            return null;
        }

        ListNode nextHead = findNextHead(secondCurNode, sortCount / 2);

        ListNode firstCurNode = head;
        head = new ListNode();
        ListNode curNode = head;

        while(firstCurNode != null && secondCurNode != null) {
            if(firstCurNode.val <= secondCurNode.val) {
                curNode.next = firstCurNode;
                curNode = firstCurNode;
                firstCurNode = firstCurNode.next;
            } else {
                curNode.next = secondCurNode;
                curNode = secondCurNode;
                secondCurNode = secondCurNode.next;
            }
        }

        while(firstCurNode != null) {
            curNode.next = firstCurNode;
            curNode = firstCurNode;
            firstCurNode = firstCurNode.next;
        }

        while(secondCurNode != null) {
            curNode.next = secondCurNode;
            curNode = secondCurNode;
            secondCurNode = secondCurNode.next;
        }
        pre.next = head.next;
        curNode.next = nextHead;
        return curNode;
    }

    private ListNode findNextHead(ListNode head, int step) {
        if(head == null) {
            return null;
        }
        ListNode curNode = head, pre = null;
        while(step > 0 && curNode != null) {
            pre = curNode;
            curNode = curNode.next;
            step--;
        }
        pre.next = null;
        return curNode;
    }
}