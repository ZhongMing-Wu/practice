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
    TreeNode* buildTree(vector<int>& inorder, vector<int>& postorder) {
        for(int i = 0; i < inorder.size(); i++) {
            loc[inorder[i]] = i;
        }
        return startBuild(inorder, postorder, 0, inorder.size() - 1, 0, postorder.size() - 1);
    }

    TreeNode* startBuild(vector<int>& inorder, vector<int>& postorder, int inLeft, int inRight, int postLeft, int postRight) {
        TreeNode* node;
        if(inLeft > inRight) {
            return NULL;
        }
        else {
            node = new TreeNode(postorder[postRight]);
            node->left = startBuild(inorder, postorder, inLeft, loc[postorder[postRight]] - 1, postLeft, postLeft + loc[postorder[postRight]] - inLeft - 1);
            node->right = startBuild(inorder, postorder, loc[postorder[postRight]] + 1, inRight, postLeft + loc[postorder[postRight]] - inLeft, postRight - 1);
        }
        return node;
    }
private:
    map<int, int> loc;
};