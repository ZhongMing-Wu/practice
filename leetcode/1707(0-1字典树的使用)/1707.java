class Solution {
    public int[] maximizeXor(int[] nums, int[][] queries) {
        BinaryTreeNode root = new BinaryTreeNode();
        for(int num : nums) {
            insert(num, root);
        }
        int n = queries.length;
        int[] ans = new int[n];
        for(int i = 0; i < n; ++i) {
            int x = queries[i][0];
            int limit = queries[i][1];
            int maxVal = maxXorVal(root, x, limit);
            if(maxVal == -1) {
                ans[i] = -1;
            } else {
                ans[i] = maxVal ^ x;
            }
        }
        return ans;
    }

    private void insert(int num, BinaryTreeNode node) {
        for(int i = 30; i >= 0; --i) {
            int index = (num >>> i) & 1;
            if(node.children[index] == null) {
                node.children[index] = new BinaryTreeNode();
            }
            node = node.children[index];
            node.minNum = Math.min(node.minNum, num);
        }
    }
    private int maxXorVal(BinaryTreeNode node, int num, int limit) {
        int maxVal = 0;
        for(int i = 30; i >= 0; --i) {
            int index = (num >>> i) & 1;
            if(node.children[1 - index] != null && node.children[1 - index].minNum <= limit) {
                maxVal += ((1 - index) << i);
                node = node.children[1 - index];
            } else {
                if(node.children[index] != null && node.children[index].minNum <= limit) {
                    maxVal += (index << i);
                } else {
                    return -1;
                }
                node = node.children[index];
            }
        }
        return maxVal;
    }
}

class BinaryTreeNode {
    public int minNum;
    public BinaryTreeNode[] children = new BinaryTreeNode[2];
    public BinaryTreeNode() {
        minNum = Integer.MAX_VALUE;
    }
}