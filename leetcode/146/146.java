class LRUCache {
    Map<Integer, Node> toNode;
    int cap, len;
    Node head, tail;
    public LRUCache(int capacity) {
        toNode = new HashMap<>(capacity);
        cap = capacity;
        len = 0;
        head = new Node();
        tail = head;
    }
    
    public int get(int key) {
        if(!toNode.containsKey(key)) {
            return -1;
        }

        if(tail.key == key) {
            return tail.value;
        }
        Node node = toNode.get(key);
        Node pre = node.pre;
        Node next = node.next;
        pre.next = next;
        if(next != null) {
            next.pre = pre;
        }

        tail.next = node;
        node.pre = tail;
        tail = node;
        return node.value;
    }
    
    public void put(int key, int value) {
        if(cap == 0) {
            return;
        }

        if(toNode.containsKey(key)) {
            Node node = toNode.get(key);
            node.value = value;
            if(tail.key != key) {
                node.pre.next = node.next;
                node.next.pre = node.pre;
                tail.next = node;
                node.pre = tail;
                tail = node;
            }
            return;
        }

        Node newNode = new Node(key, value);
        if(len == cap) {
            Node firstNode = head.next;
            head.next = firstNode.next;
            if(firstNode.next != null) {
                firstNode.next.pre = head;
            }
            toNode.remove(firstNode.key);
            if(tail.key == firstNode.key) {
                tail = firstNode.pre;
            }
            len--;
        }
        tail.next = newNode;
        newNode.pre = tail;
        tail = newNode;
        toNode.put(key, newNode);
        len++;
    }
}

class Node {
    public int key;
    public int value;
    public Node pre;
    public Node next;

    public Node() {}
    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */