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
    TreeNode* buildTree(vector<int>& preorder, vector<int>& inorder) {
        for(int i = 0; i < inorder.size(); i++) {
            loc[inorder[i]] = i;
        }

        return startBuild(preorder, inorder, 0, preorder.size() - 1, 0, inorder.size() - 1);
    }

    TreeNode* startBuild(vector<int>& preorder, vector<int>& inorder, int preLeft, int preRight, int inLeft, int inRight) {
        TreeNode* node;
        if(preLeft > preRight) {
            return NULL;
        }
        else {
            node = new TreeNode(preorder[preLeft]);
            node->left = startBuild(preorder, inorder, preLeft + 1, preLeft + loc[preorder[preLeft]] - inLeft, inLeft, loc[preorder[preLeft]] - 1);
            node->right = startBuild(preorder, inorder, preLeft + loc[preorder[preLeft]] - inLeft + 1, preRight, loc[preorder[preLeft]] + 1, inRight);
        }
        return node;
    }
private:
    map<int, int> loc;
};