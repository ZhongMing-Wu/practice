class MyStack {

    private LinkedList<Integer> myStack;
    /** Initialize your data structure here. */
    public MyStack() {
        myStack = new LinkedList<Integer>();
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        myStack.offer(x);
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        int val = myStack.pollLast();
        return val;
    }
    
    /** Get the top element. */
    public int top() {
        return myStack.peekLast();
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return myStack.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */