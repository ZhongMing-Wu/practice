class SummaryRanges {
    private class Node {
        public int left, right;
        public Node next;
        public Node(int left, int right, Node next) {
            this.left = left;
            this.right = right;
            this.next = next;
        }
    }

    TreeMap<Integer, Node> toNode;
    /** Initialize your data structure here. */
    public SummaryRanges() {
        Node node = new Node(-2, -2, null);
        toNode = new TreeMap<>();
        toNode.put(-2, node);
    }
    
    public void addNum(int val) {
        Node pre = toNode.get(toNode.lowerKey(val));
        Node cur = pre.next;
        if(cur == null) {
            if(pre.right + 1 == val) {
                toNode.remove(pre.right);
                pre.right = val;
                toNode.put(pre.right, pre);
                return;
            } 
            Node newNode = new Node(val, val, null);
            pre.next = newNode;
            toNode.put(val, newNode);
            return;
        }
        if(val >= cur.left) {
            return;
        }
        if(pre.right + 1 == val && cur.left == val + 1) {
            toNode.remove(pre.right);
            pre.next = cur.next;
            pre.right = cur.right;
            cur.next = null;
            toNode.put(pre.right, pre);
            return;
        }
        if(pre.right + 1 == val) {
            toNode.remove(pre.right);
            pre.right = val;
            toNode.put(val, pre);
            return;
        }
        if(cur.left == val + 1) {
            cur.left = val;
            return;
        }
        Node newNode = new Node(val, val, null);
        toNode.put(val, newNode);
        newNode.next = cur;
        pre.next = newNode;
    }
    
    public int[][] getIntervals() {
        List<int[]> list = new ArrayList<>();
        Node head = toNode.get(-2);
        while(head.next != null) {
            head = head.next;
            int[] temp = new int[2];
            temp[0] = head.left;
            temp[1] = head.right;
            list.add(temp);
        }
        return list.toArray(new int[0][0]);
    }
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(val);
 * int[][] param_2 = obj.getIntervals();
 */