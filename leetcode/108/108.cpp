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
    TreeNode* sortedArrayToBST(vector<int>& nums) {
        return startBuild(nums, 0, nums.size() - 1);
    }

    TreeNode* startBuild(vector<int>& nums, int left, int right) {
        TreeNode* node;
        if(left > right) {
            return NULL;
        }
        else {
            node = new TreeNode(nums[(left + right) / 2]);
            node->left = startBuild(nums, left, (left + right) / 2 - 1);
            node->right = startBuild(nums, (left + right) / 2 + 1, right);
        }
        return node;
    }
};