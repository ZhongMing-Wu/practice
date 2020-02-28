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
    int minDepth(TreeNode* root) {
        if(!root)
            return 0;
        int minDeep = INT_MAX;
        DFS(root, 1, minDeep);
        return minDeep;
    }

    void DFS(TreeNode* root, int nodeCount, int& minDeep) {
        if(!root->left && !root->right) {
            minDeep = min(minDeep, nodeCount);
        }
        else {
            if(root->left) {
                DFS(root->left, nodeCount + 1, minDeep);
            }
            if(root->right) {
                DFS(root->right, nodeCount + 1, minDeep);
            }
        }
    }
};