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
    ListNode* rotateRight(ListNode* head, int k) {
        if(!head)
            return head;
        int length = 0;
        ListNode* ptr = head;
        while(ptr != NULL) {
            length++;
            ptr = ptr->next;
        }
        k %= length;
        ListNode *current = head, *tail = head;
        while(k) {
            tail = tail->next;
            k--;
        } 
        while(tail->next) {
            tail = tail->next;
            current = current->next;
        }
        tail->next  = head;
        head = current->next;
        current->next = NULL;
        return head;
    }
};