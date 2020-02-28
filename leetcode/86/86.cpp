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
    ListNode* partition(ListNode* head, int x) {
        ListNode* preHead = new ListNode(INT_MIN);
        preHead->next = head;
        ListNode *preNode = preHead, *nextNode = preHead, *current = head;
        while(current) {
            if(current->val >= x) {
                preNode = current;
                current = current->next;
            }
            else {
                if(nextNode->next == current) {
                    nextNode = current;
                    preNode = current;
                    current = current->next;
                }
                else {
                    preNode->next = current->next;
                    current->next = nextNode->next;
                    nextNode->next = current;
                    nextNode = current;
                    current = preNode->next;
                }
            }
        }
        return preHead->next; 
    }
};