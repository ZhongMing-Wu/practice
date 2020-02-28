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
    vector<TreeNode*> generateTrees(int n) {
        vector<TreeNode*> ans;
        if(n == 0)
            return ans;
        return startGenerateTrees(1, n);
    }
    
    vector<TreeNode*> startGenerateTrees(int start, int end) {
        vector<TreeNode*> trees, leftTrees, rightTrees;
        if(start > end) {
            trees.push_back(NULL);
            return trees;
        }

        for(int i = start; i <= end; i++) {
            leftTrees = startGenerateTrees(start, i - 1);
            rightTrees = startGenerateTrees(i + 1, end);

            for(int m = 0; m < leftTrees.size(); m++) {
                for(int j = 0; j < rightTrees.size(); j++) {
                    TreeNode* node = new TreeNode(i);
                    node->left = leftTrees[m];
                    node->right = rightTrees[j];
                    trees.push_back(node);
                }
            }
        }
        return trees;
    }
};