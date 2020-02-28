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
    bool isValidBST(TreeNode* root) {
        return judgeIsBST(root, LLONG_MIN, LLONG_MAX);
    }

    bool judgeIsBST(TreeNode* root, long long lower, long long upper) {
        if(!root)
            return true;
        long long left = LLONG_MIN, right = LLONG_MAX;
        
        if(root->left) {
            left = root->left->val;
        }
        if(root->right) {
            right = root->right->val;
        }
        
        if(root->val <= lower || root->val >= upper) return false;
        if(judgeIsBST(root->left, lower, root->val) && judgeIsBST(root->right, root->val, upper) && root->val > left && root->val < right) {
            return true;
        }
        else {
            return false;
        }
    }
};