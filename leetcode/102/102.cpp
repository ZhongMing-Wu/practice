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
    vector<vector<int>> levelOrder(TreeNode* root) {
        vector<int> oneLayer;
        vector<vector<int>> ans;
        if(!root) {
            return ans;
        }
        TreeNode *ptr, *tail = root;
        queue<TreeNode*> _queue;
        _queue.push(root);
        while(!_queue.empty()) {
            ptr = _queue.front();
            if(ptr->left) {
                _queue.push(ptr->left);
            }
            if(ptr->right) {
                _queue.push(ptr->right);
            }
            _queue.pop();
            oneLayer.push_back(ptr->val);
            if(ptr == tail) {
                ans.push_back(oneLayer);
                oneLayer.clear();
                tail = _queue.back();
            }
        }
        return ans;
    }
};