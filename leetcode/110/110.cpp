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
    bool isBalanced(TreeNode* root) {
        return deep(root) != -1;
    }
    int deep(TreeNode* root) {
        if(!root)
            return 0;
        int left = deep(root->left);
        if(left == -1)
            return -1;
        int right = deep(root->right);
        if(right == -1)
            return -1;
        return abs(left - right) > 1 ? -1 : (max(left, right) + 1);
    }
};