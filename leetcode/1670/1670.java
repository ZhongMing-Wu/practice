class FrontMiddleBackQueue {
    static class Node {
        public Node preNode, nextNode;
        public int val;

        public Node(int val) {
            this.val = val;
            preNode = null;
            nextNode = null;
        }
    }

    private int queueSize;
    private Node frontNode, middleNode, tailNode;
    public FrontMiddleBackQueue() {
        frontNode = middleNode = tailNode = null;
        queueSize = 0;
    }
    
    public void pushFront(int val) {
        if(frontNode == null) {
            frontNode = new Node(val);
            middleNode = frontNode;
            tailNode = frontNode;
            ++queueSize;
            return;
        }

        Node newFront = new Node(val);
        frontNode.preNode = newFront;
        newFront.nextNode = frontNode;

        ++queueSize;
        if(queueSize % 2 == 0) {
            middleNode = middleNode.preNode;
        }

        frontNode = newFront;
    }
    
    public void pushMiddle(int val) {
        if(middleNode == null) {
            frontNode = new Node(val);
            middleNode = frontNode;
            tailNode = frontNode;
            ++queueSize;
            return;
        } 
        Node newMiddle = new Node(val);
        if(queueSize % 2 == 0) {
            newMiddle.nextNode = middleNode.nextNode;
            newMiddle.preNode = middleNode;
            middleNode.nextNode.preNode = newMiddle;
            middleNode.nextNode = newMiddle;
        } else {
            newMiddle.nextNode = middleNode;
            newMiddle.preNode = middleNode.preNode;
            if(queueSize != 1) {
                middleNode.preNode.nextNode = newMiddle;
            } else {
                frontNode = newMiddle;
            }
            middleNode.preNode = newMiddle;
        }

        middleNode = newMiddle;
        ++queueSize;
    }
    
    public void pushBack(int val) {
        if(tailNode == null) {
            frontNode = new Node(val);
            middleNode = frontNode;
            tailNode = frontNode;
            ++queueSize;
            return;
        }  

        Node newTail = new Node(val);
        tailNode.nextNode = newTail;
        newTail.preNode = tailNode;
        tailNode = newTail;

        ++queueSize;
        if(queueSize % 2 == 1) {
            middleNode = middleNode.nextNode;
        } 
    }
    
    public int popFront() {
        if(queueSize == 0) {
            return -1;
        }
        
        int val = frontNode.val;
        frontNode = frontNode.nextNode;
        --queueSize;

        if(queueSize == 0) {
            middleNode = null;
            tailNode = null;
            return val;
        } else if(queueSize == 1) {
            middleNode = frontNode;
            frontNode.preNode = null;
        } else if(queueSize % 2 == 1) {
            middleNode = middleNode.nextNode;
        }

        frontNode.preNode = null;
        return val;
    }
    
    public int popMiddle() {
        if(queueSize == 0) {
            return -1;
        }

        int val = middleNode.val;
        --queueSize;
        if(queueSize == 0) {
            middleNode = null;
            frontNode = null;
            tailNode = null;
        } else if(queueSize == 1) {
            frontNode = middleNode.nextNode;
            middleNode = middleNode.nextNode;
            middleNode.preNode = null;  
        } else if(queueSize % 2 == 0) {
            Node temp = middleNode.preNode;
            temp.nextNode = middleNode.nextNode;
            middleNode.nextNode.preNode = temp;
            middleNode = temp;
        } else {
            Node temp = middleNode.nextNode;
            temp.preNode = middleNode.preNode;
            middleNode.preNode.nextNode = temp;
            middleNode = temp;
        }

        return val;
    }
    
    public int popBack() {
        if(queueSize == 0) {
            return -1;
        }

        int val = tailNode.val;
        tailNode = tailNode.preNode;
        --queueSize;

        if(queueSize == 0) {
            frontNode = null;
            middleNode = null;
            return val;
        } else if(queueSize % 2 == 0) {
            middleNode = middleNode.preNode;
        }
        tailNode.nextNode = null;

        return val;
    }
}

/**
 * Your FrontMiddleBackQueue object will be instantiated and called as such:
 * FrontMiddleBackQueue obj = new FrontMiddleBackQueue();
 * obj.pushFront(val);
 * obj.pushMiddle(val);
 * obj.pushBack(val);
 * int param_4 = obj.popFront();
 * int param_5 = obj.popMiddle();
 * int param_6 = obj.popBack();
 */