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
    int maxPathSum(TreeNode* root) {
        ans = INT_MIN;
        startFind(root);
        return ans;
    }

    int startFind(TreeNode* root) {
        if(!root) {
            return 0;
        }
        int leftAns = max(0, startFind(root->left));
        int rightAns = max(0, startFind(root->right));
        ans = max(ans, leftAns + rightAns + root->val);
        return max(leftAns + root->val, rightAns + root->val);
    }
private:
    int ans;
};