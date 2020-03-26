/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/
class Solution {
    public Node cloneGraph(Node node) {
        if(node == null) {
            return null;
        }
        LinkedList<Node> oldQueue = new LinkedList<>();
        LinkedList<Node> newQueue = new LinkedList<>();
        Map<Node, Integer> visited = new HashMap<>();
        oldQueue.offer(node);
        Node newNode = null, oldNode = null, oldNodeSon = null, newNodeSon = null;
        Node rootNode = new Node(node.val);
        newQueue.offer(rootNode);
        List<Node> tempList = null;
        while(!oldQueue.isEmpty()) {
            oldNode = oldQueue.poll();
            newNode = newQueue.poll();
            tempList = oldNode.neighbors;
            for(int i = 0; i < tempList.size(); i++) {
                oldNodeSon = tempList.get(i);
                if(!visited.containsKey(oldNodeSon)) {
                    if(!oldQueue.contains(oldNodeSon)) {
                        newNodeSon = new Node(oldNodeSon.val);
                        oldQueue.offer(oldNodeSon);
                        newQueue.offer(newNodeSon);
                    }
                    else {
                        newNodeSon = newQueue.get(oldQueue.indexOf(oldNodeSon));
                    }
                    newNode.neighbors.add(newNodeSon);
                    newNodeSon.neighbors.add(newNode);
                }
            }
            visited.put(oldNode, 1);
        }
        return rootNode;
    }
}