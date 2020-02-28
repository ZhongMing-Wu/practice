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
    ListNode* swapPairs(ListNode* head) {
        if(head == NULL || head->next == NULL)
            return head;
        ListNode *first ,*second ,*third;
        
        ListNode *newHead = new ListNode(1); //新建两个节点
        ListNode *tail = new ListNode(1);
        
        newHead->next = tail;
        tail->next = head;
        first = head;
        second = first->next;
        third = second->next;
        while(first != NULL && second != NULL) {
            second->next = first;
            first->next = NULL;
            tail->next = second;
            tail = first;
            first = third;
            if(first != NULL) {
                second = first->next;
                if(second != NULL)
                    third = second->next;
            }
        }
        //返回时，注意返回的是第三个节点开始的内容
        if(first == NULL) 
            return newHead->next->next;
        else {
            tail->next = first;
            return newHead->next->next;
        }
    }
};