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
        vector<int> ans;
        TreeNode *p = root;
        stack<TreeNode*> s;
        while(!s.empty() || p) {
            if(p) {
                s.push(p);
                p = p->left;
            }
            else {
                p = s.top();
                ans.push_back(p->val);
                s.pop();
                p = p->right;
            }
        }
        return ans;
    }
};