/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:
    TreeNode* sortedListToBST(ListNode* head) {
        if(!head)
            return NULL;
        else 
            return startBuild(head);
    }

    TreeNode* startBuild(ListNode* head) {
        ListNode *fastNode = head, *slowNode = head, *preNode = NULL;
        TreeNode* node;
        if(!head->next) {
            node = new TreeNode(head->val);
        }
        else {
            while(fastNode && fastNode->next) {
                preNode = slowNode;
                slowNode = slowNode->next;
                fastNode = fastNode->next->next; 
            }
            node = new TreeNode(slowNode->val);
            if(preNode)
                preNode->next = NULL;
            if(slowNode->next) {
                node->right = startBuild(slowNode->next);
            }
            node->left = startBuild(head);
        }
        return node;
    }
};