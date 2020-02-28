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
    void recoverTree(TreeNode* root) {
        first = second = NULL;
        inOrder(root);
        int temp = first->val;
        first->val = second->val;
        second->val = temp;
    }

    void inOrder(TreeNode* root) {
	    TreeNode *pre = NULL; //pre节点用于存储正访问节点的前驱节点的地址
	    while (1) {
		    if (root->left) {
			    findPreNode(root)->right = root;
			    root = root->left;
		    }
		    else {
    label:
			    //cout << root->val << " ";
                if(pre) {
                    if(pre->val > root->val) {
                        if(!first) {
                            first = pre;
                            second = root;
                        }
                        else {
                            second = root;
                        }
                    }
                }
                else {
                    pre = root;
                }
			    if (!root->right) {
				    break;
			    }
			    pre = root;
			    root = root->right;
			    pre->right = NULL;
			    if (findPreNode(root) == NULL || findPreNode(root) != pre) {
				    pre->right = root;
			    }
			    else {
				    goto label;
			    }
		    }
	    }
    }

    //用于查找某个节点在中序遍历中的前序节点
    TreeNode* findPreNode(TreeNode* node) {
	    if (node->left) {
		    TreeNode* aimNode = node->left;
		    while (aimNode->right) {
			    aimNode = aimNode->right;
		    }
		    return aimNode;
	    }
	    else {
		    return NULL;
	    }
    }
private:
    TreeNode *first, *second;
};