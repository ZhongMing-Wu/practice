class MyCircularQueue {

    int[] queue;
    int head, tail, len;
    public MyCircularQueue(int k) {
        queue = new int[k + 1];
        head = 0;
        tail = 0;
        len = k + 1;
    }
    
    public boolean enQueue(int value) {
        if((tail + 1) % len == head) {
            return false;
        }
        queue[tail] = value;
        tail = (tail + 1) % len;
        return true;
    }
    
    public boolean deQueue() {
        if(tail == head) {
            return false;
        }
        head = (head + 1) % len;
        return true;
    }
    
    public int Front() {
        if(tail == head) {
            return -1;
        }
        return queue[head];
    }
    
    public int Rear() {
        if(tail == head) {
            return -1;
        }
        int index = (tail + len - 1) % len;
        return queue[index];
    }
    
    public boolean isEmpty() {
        return tail == head;
    }
    
    public boolean isFull() {
        return (tail + 1) % len == head;
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */