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
    ListNode* mergeKLists(vector<ListNode*>& lists) {
        if(lists.size() == 0)
            return NULL;
        int length = 1;
        int start;
        while(length * 2 <= lists.size()){
            start = 0;
            while(start <= lists.size()-1 && start+length <= lists.size()-1){
                lists[start] = mergeTwoLists(lists[start] ,lists[start+length]);
                start = start + 2*length;
            }
            length *= 2;
        }
        if(length == lists.size()) {
            return lists[0];
        }
        else {
            return mergeTwoLists(lists[0] ,lists[length]);
        }
    }
    
    ListNode* mergeTwoLists(ListNode* list1 ,ListNode* list2) {
        if(list1 == NULL) {
            list1 = list2;
            return list1;
        }
        if(list2 == NULL)
            return list1;
        
        ListNode *head ,*tail;
        if(list1->val < list2->val) {
            head = list1;
            list1 = list1->next;
        }
        else {
            head = list2;
            list2 = list2->next;
        }
        tail = head;
        while(list1 != NULL && list2 != NULL) {
            if(list1->val < list2->val){
                tail->next = list1;
                list1 = list1->next;
                tail = tail->next;
            }
            else {
                tail->next = list2;
                list2 = list2->next;
                tail = tail->next;
            }
        }
        if(list1 != NULL)
            tail->next = list1;
        if(list2 != NULL)
            tail->next = list2;
        return head;
    }
};