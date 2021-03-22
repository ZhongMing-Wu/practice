class Solution {
    public int countPairs(int[] nums, int low, int high) {
        int count = 0;
        BinaryTreeNode root = new BinaryTreeNode();
        for(int num : nums) {
            count += smaller(num, high + 1, root) - smaller(num, low, root);
            insert(num, root);
        }
        return count;
    }

    private void insert(int num, BinaryTreeNode node) {
        for(int i = 31; i >= 0; --i) {
            int index = (num >>> i) & 1;
            if(node.children[index] == null) {
                node.children[index] = new BinaryTreeNode();
            }
            ++node.children[index].score;
            node = node.children[index];
        }
    }
    private int smaller(int num, int low, BinaryTreeNode node) {
        int count = 0;
        for(int i = 31; i >= 0; --i) {
            if(node == null) {
                return count;
            }
            int lowIndex = (low >>> i) & 1;
            int index = (num >>> i) & 1;
            if(lowIndex == 1) {
                if(node.children[index] != null) {
                    count += node.children[index].score;
                }
                node = node.children[1 - index];
            } else {
                node = node.children[index];
            }
        }
        return count;
    }
}

class BinaryTreeNode {
    int score;
    BinaryTreeNode[] children = new BinaryTreeNode[2];
}