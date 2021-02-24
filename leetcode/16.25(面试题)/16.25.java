class LRUCache {

    static class Node {
        public int val;
        public int key;
        public Node next;
        public Node pre;
    }
    private int size, curSize;
    HashMap<Integer, Node> toNode;
    Node head, tail;
    public LRUCache(int capacity) {
        size = capacity;
        curSize = 0;
        toNode = new HashMap<>(size);
        head = tail = null;
    }
    
    public int get(int key) {
        Node node = toNode.getOrDefault(key, null);
        if(node == null) {
            return -1;
        }
    
        if(node == head && node != tail) {
            head = head.next;
            head.pre = null;
            tail.next = node;
            node.pre = tail;
            tail = node;
            return node.val;
        }

        if(node == tail) {
            return node.val;
        }

        node.next.pre = node.pre;
        node.pre.next = node.next;
        tail.next = node;
        node.pre = tail;
        node.next = null;
        tail = node;

        return node.val;
    }
    
    public void put(int key, int value) {
        if(toNode.containsKey(key)) {
            Node temp = toNode.get(key);
            temp.val = value;
            get(key);
            return;
        }
        if(curSize == size) {
            Node temp = head;
            head = head.next;
            temp.next = null;
            toNode.remove(temp.key);
            --curSize;
            if(curSize == 0) {
                tail = null;
            } else {
                head.pre = null;
            }
        }

        Node newNode = new Node();
        newNode.val = value;
        newNode.key = key;
        if(tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.pre = tail;
            tail = newNode;
        }
        toNode.put(key, newNode);
        ++curSize;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */