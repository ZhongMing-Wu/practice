/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
class Solution {
public:
    ListNode* reverseBetween(ListNode* head, int m, int n) {
        ListNode *front = head, *tail, *preFront;
        while(m > 1) {
            preFront = front;
            front = front->next;
            m--;
            n--;
        }
        tail = front->next;
        ListNode *nextTail, *preTail = front;
        while(n > 1) {
            nextTail = tail->next;
            tail->next = preTail;
            preTail = tail;
            tail = nextTail;
            n--;
        }
        if(front == head) {
            head = preTail;
            front->next = tail;
        }
        else {
            preFront->next = preTail;
            front->next = tail;            
        }
        return head;
    }
};