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
    vector<vector<int>> pathSum(TreeNode* root, int sum) {
        if(!root) {
            return anses;
        }
        int currentSum = 0;
        DFS(root, sum, currentSum);
        return anses;
    }

    void DFS(TreeNode* root, int sum, int& currentSum) {
        oneAns.push_back(root->val);
        currentSum += root->val;
        if(!root->left && !root->right) {
            if(currentSum == sum) {
                anses.push_back(oneAns);
            }
        }
        else {
            if(root->left) {
                DFS(root->left, sum, currentSum);
            }
            if(root->right) {
                DFS(root->right, sum, currentSum);
            }
        }
        currentSum -= root->val;
        oneAns.pop_back();
    }
private:
    vector<int> oneAns;
    vector<vector<int>> anses;
};