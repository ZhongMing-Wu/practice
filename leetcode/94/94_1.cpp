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
    vector<int> inorderTraversal(TreeNode* root) {
        if(!root)
            return ans;
        if(root->left)
            inorderTraversal(root->left);
        ans.push_back(root->val);
        if(root->right)
            inorderTraversal(root->right);
        return ans;
    }
private:
    vector<int> ans;
};