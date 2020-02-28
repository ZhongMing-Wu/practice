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
    void flatten(TreeNode* root) {
        TreeNode *ptr = root, *ptr1;
        while(ptr) {
            if(!ptr->left) {
                ptr = ptr->right; 
            }
            else {
                ptr1 = ptr->left;
                while(ptr1->right) {
                    ptr1 = ptr1->right;
                }
                ptr1->right = ptr->right;
                ptr->right = ptr->left;
                ptr->left = NULL;
            }
        }
    }
};