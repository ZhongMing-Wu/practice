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
    ListNode* deleteDuplicates(ListNode* head) {
        if(head == NULL || head->next == NULL) {
            return head;
        }
        int currentVal = head->val;
        int occurCount = 0;
        ListNode *insertLocation = head, *ptr = head, *preInsertLocation = NULL; //使用ptr对链表进行遍历
        while(ptr) {
            if(ptr->val != currentVal) {
                if(occurCount == 1) {
                    insertLocation->val = currentVal;    
                    preInsertLocation = insertLocation;
                    insertLocation = insertLocation->next;
                }
                currentVal = ptr->val;
                occurCount = 0;
            }
            else {
                occurCount++;
                ptr = ptr->next;
            }
        }
        if(occurCount == 1) {
            insertLocation->val = currentVal;
            preInsertLocation = insertLocation;
        }
        if(preInsertLocation){
            preInsertLocation->next = NULL;
            return head;
        }
        else {
            return NULL;
        }
    }
};