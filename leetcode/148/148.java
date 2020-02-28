/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode sortList(ListNode head) {
        int len = 0;
        ListNode preHead = new ListNode(0);
        preHead.next = head;
        while(head != null) {
            len++;
            head = head.next;
        }
        for(int i = 2; i < len * 2; i *= 2) {
            startSort(preHead, i);
        }
        return preHead.next;
    }

    void startSort(ListNode preHead, int len) {
        ListNode firstNode, secondNode = preHead.next, preNode = preHead;

        while(true) {
            firstNode = secondNode;
            for(int i = 0; i < len / 2; i++) {
                if(secondNode.next != null) {
                    secondNode = secondNode.next;
                }
                else {
                    secondNode = null;
                    break;
                }
            }

            if(secondNode == null) {
                return;
            }
            else {
                int firstNodeVisited = 0, secondNodeVisited = 0;
                while(firstNodeVisited < len / 2 && secondNodeVisited < len / 2 && firstNode != null && secondNode != null) {
                    if(firstNode.val < secondNode.val) {
                        preNode.next = firstNode;
                        preNode = firstNode;
                        firstNode = firstNode.next;
                        firstNodeVisited++;
                    }
                    else {
                        preNode.next = secondNode;
                        preNode = secondNode;
                        secondNode = secondNode.next;
                        secondNodeVisited++;
                    }
                }
                if(secondNode == null) {
                    while(firstNodeVisited < len / 2) {
                        preNode.next = firstNode;
                        preNode = firstNode;
                        firstNode = firstNode.next;
                        firstNodeVisited++;
                    }
                }
                else {
                    if(firstNodeVisited < len / 2) {
                        while(firstNodeVisited < len / 2) {
                            preNode.next = firstNode;
                            preNode = firstNode;
                            firstNode = firstNode.next;
                            firstNodeVisited++;
                        }
                    }
                    else {
                        while(secondNodeVisited < len / 2 && secondNode != null) {
                            preNode.next = secondNode;
                            preNode = secondNode;
                            secondNode = secondNode.next;
                            secondNodeVisited++;
                        }
                    }
                }
            }
            if(secondNode == null) {
                preNode.next = null;
                return;
            }
            else {
                preNode.next = secondNode;
            }
        }
    }
}