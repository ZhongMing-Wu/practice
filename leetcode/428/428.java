/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Codec {
    // Encodes a tree to a single string.
    public String serialize(Node root) {
        if(root == null) {
            return "";
        }
        StringBuilder sb = startSerialize(root);
        return sb.toString();
    }

    public StringBuilder startSerialize(Node node) {
        StringBuilder sb = new StringBuilder();
        sb.append(node.val).append('[');
        if(node.children == null || node.children.size() == 0) {
            sb.append(']');
            return sb;
        }
        for(Node child : node.children) {
            sb.append(startSerialize(child));
        }
        sb.append(']');
        return sb;
    }
	
    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if(data.equals("")) {
            return null;
        }

        HashMap<Integer, Integer> leftToRightBracketIndex = new HashMap<>();
        Deque<Integer> leftBracketsIndex = new LinkedList<>();
        for(int i = 0; i < data.length(); i++) {
            if(data.charAt(i) == '[') {
                leftBracketsIndex.offerLast(i);
                continue;
            }
            if(data.charAt(i) == ']' && !leftBracketsIndex.isEmpty()) {
                leftToRightBracketIndex.put(leftBracketsIndex.pollLast(), i);
            }
        }

        List<Node> children = startDeserialize(data, 0, data.length(), leftToRightBracketIndex);
        return children.get(0);
    }

    public List<Node> startDeserialize(String data, int begin, int end, HashMap<Integer, Integer> leftToRightBracketIndex) {
        List<Node> children = new ArrayList<>();
        int start = begin;
        while(start < end) {
            int leftBracketLoc = findNextLeftBracketLoc(data, start);
            int val = Integer.valueOf(data.substring(start, leftBracketLoc));
            int rightBracketLoc = leftToRightBracketIndex.get(leftBracketLoc);
            Node child = new Node(val);
            if(leftBracketLoc + 1 < rightBracketLoc) {
                child.children = startDeserialize(data, leftBracketLoc + 1, rightBracketLoc, leftToRightBracketIndex);
            } else {
                child.children = new ArrayList<>();
            }
            children.add(child);
            start = rightBracketLoc + 1;
        }
        return children;
    }

    public int findNextLeftBracketLoc(String data, int start) {
        int len = data.length(), index = start;
        while(index < len && data.charAt(index) != '[') {
            index++;
        }
        return index;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));