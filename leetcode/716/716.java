class MaxStack {
    Deque<Integer> stack1, stack2;
    /** initialize your data structure here. */
    public MaxStack() {
        stack1 = new LinkedList<>();
        stack2 = new LinkedList<>();
    }
    
    public void push(int x) {
        stack1.offerLast(x);
        if(stack2.isEmpty()) {
            stack2.offerLast(x);
            return;
        }
        int peekVal = stack2.peekLast();
        stack2.offerLast(Math.max(x, peekVal));
    }
    
    public int pop() {
        stack2.pollLast();
        return stack1.pollLast();
    }
    
    public int top() {
        return stack1.peekLast();
    }
    
    public int peekMax() {
        return stack2.peekLast();
    }
    
    public int popMax() {
        Deque<Integer> stack = new LinkedList<>();
        int val = stack2.peekLast();
        while(true) {
            int topVal = stack1.pollLast();
            stack2.pollLast();
            if(topVal != val) {
                stack.offerLast(topVal);
                continue;
            }
            while(!stack.isEmpty()) {
                push(stack.pollLast());
            }
            return val;
        }
    }
}

/**
 * Your MaxStack object will be instantiated and called as such:
 * MaxStack obj = new MaxStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.peekMax();
 * int param_5 = obj.popMax();
 */