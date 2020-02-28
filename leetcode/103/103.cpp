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
    vector<vector<int>> zigzagLevelOrder(TreeNode* root) {
        stack<TreeNode*> _stack;
        vector<vector<int>> ans;
        vector<int> oneAns;
        vector<TreeNode*> oneLayerNode;
        if(!root) {
            return ans;
        }
        int flag = 1;   //flag 为1表示当前遍历顺序为从左到右，0表示从右到左
        _stack.push(root);
        TreeNode* ptr;
        while(!_stack.empty()) {
            while(!_stack.empty()) {
                oneLayerNode.push_back(_stack.top());
                _stack.pop();
            }
            //当前遍历顺序为从左到右。则下一层遍历顺序为从右到左
            if(flag) {
                for(int i = 0; i < oneLayerNode.size(); i++) {
                    if(oneLayerNode[i]->left) {
                        _stack.push(oneLayerNode[i]->left);
                    }

                    if(oneLayerNode[i]->right) {
                        _stack.push(oneLayerNode[i]->right);
                    }
                    oneAns.push_back(oneLayerNode[i]->val);
                }
                ans.push_back(oneAns);
                flag = 0;
            }
            else {
                for(int i = 0; i < oneLayerNode.size(); i++) {
                    if(oneLayerNode[i]->right) {
                        _stack.push(oneLayerNode[i]->right);
                    }

                    if(oneLayerNode[i]->left) {
                        _stack.push(oneLayerNode[i]->left);
                    }
                    oneAns.push_back(oneLayerNode[i]->val);
                }
                ans.push_back(oneAns);
                flag = 1;
            }
            oneAns.clear();
            oneLayerNode.clear();
        }
        return ans;
    }
};