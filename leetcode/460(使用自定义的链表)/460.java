class LFUCache {
    Map<Integer, Node> keyCache;
    Map<Integer, MyLinkedList> freqCache;
    int capacity, curSize, minFreq;
    public LFUCache(int capacity) {
        keyCache = new HashMap<>();
        freqCache = new HashMap<>();
        this.capacity = capacity;
        curSize = 0;
        minFreq = 1;
    }
    
    public int get(int key) {
        if(!keyCache.containsKey(key)) {
            return -1;
        }

        Node node = keyCache.get(key);
        int freq = ++node.freq;
        MyLinkedList list = freqCache.get(freq - 1);
        list.removeNode(node);
        if(list.size == 0) {
            freqCache.remove(freq - 1);
            if(freq - 1 == minFreq) {
                ++minFreq;
            }
        }

        if(!freqCache.containsKey(freq)) {
            freqCache.put(freq, new MyLinkedList());
        }

        list = freqCache.get(freq);
        list.addLast(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        if(capacity == 0) {
            return;
        }
        if(keyCache.containsKey(key)) {
            get(key);
            Node node = keyCache.get(key);
            node.val = value;
        } else {
            Node node = new Node();
            node.val = value;
            node.freq = 1;
            node.key = key;
            keyCache.put(key, node);
            if(curSize == capacity) {
                MyLinkedList list = freqCache.get(minFreq);
                Node deleteNode = list.head.next;
                keyCache.remove(deleteNode.key);
                list.removeNode(deleteNode);

                if(list.size == 0) {
                    freqCache.remove(minFreq);
                }
                if(!freqCache.containsKey(1)) {
                    freqCache.put(1, new MyLinkedList());
                }
                list = freqCache.get(1);
                list.addLast(node);
            } else {
                if(!freqCache.containsKey(1)) {
                    freqCache.put(1, new MyLinkedList());
                }
                MyLinkedList list = freqCache.get(1);
                list.addLast(node);
                ++curSize;
            }
            minFreq = 1;
        }
    }
}

class Node {
    public int key;
    public int val;
    public int freq;
    public Node next;
    public Node pre;
}
class MyLinkedList {
    public Node head, tail;
    public int size;
    public MyLinkedList() {
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;

        size = 0;
    }

    public void removeNode(Node node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
        node.pre = null;
        node.next = null;
        --size;
    }

    public void addLast(Node node) {
        Node temp = tail.pre;
        temp.next = node;
        node.pre = temp;
        node.next = tail;
        tail.pre = node;
        ++size;
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */