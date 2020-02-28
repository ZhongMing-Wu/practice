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
    ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
        int flag=0;
        ListNode *pl1=l1;
        ListNode *pl2=l2;
        while(pl1&&pl2){
            pl1=pl1->next;
            pl2=pl2->next;
        }
        ListNode *longList;
        ListNode *shortList;
        if(pl1){
            longList=l1;
            shortList=l2;
        }else{
            longList=l2;
            shortList=l1;
        }
        ListNode *head=longList;
        ListNode *preLongList;
        int tempSum;
        while(longList&&shortList){
            tempSum=(longList->val+shortList->val+flag)%10;
            if((longList->val+shortList->val+flag)>=10)
                flag=1;
            else
                flag=0;
            longList->val=tempSum;
            preLongList=longList;
            longList=longList->next;
            shortList=shortList->next;
        }
        if(longList){
            while(longList){
                tempSum=(longList->val+flag)%10;
                if((longList->val+flag)>=10){
                    flag=1;
                    longList->val=tempSum;
                    preLongList=longList;
                    longList=longList->next;
                }
                else{
                    flag=0;
                    longList->val=tempSum;
                    break;
                }
            }
            if(flag){
                preLongList->next=new ListNode(1);
            }
        }
        else{
            if(flag){
                preLongList->next=new ListNode(1);
            }
        }
        return head;
    }
};