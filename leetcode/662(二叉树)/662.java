class Solution {
    public int widthOfBinaryTree(TreeNode root) {
        if(root == null) {
            return 0;
        }   
        Queue<PositionNode> queue = new LinkedList<>();
        queue.offer(new PositionNode(1, 1, root));
        int curDepth = 0, leftMost = 1, maxWidth = 0;
        while(!queue.isEmpty()) {
            ++curDepth;
            leftMost = queue.peek().pos;
            while(!queue.isEmpty() && queue.peek().depth == curDepth) {
                PositionNode pNode = queue.poll();
                if(pNode.node.left != null) {
                    queue.offer(new PositionNode(curDepth + 1, pNode.pos * 2, pNode.node.left));
                }
                if(pNode.node.right != null) {
                    queue.offer(new PositionNode(curDepth + 1, pNode.pos * 2 + 1, pNode.node.right));
                }
                maxWidth = Math.max(maxWidth, pNode.pos - leftMost + 1);
            }
        }
        return maxWidth;
    }
}

class PositionNode {
    public int depth, pos;
    public TreeNode node;
    public PositionNode(int depth, int pos, TreeNode node) {
        this.depth = depth;
        this.pos = pos;
        this.node = node;
    }
}