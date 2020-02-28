class MinStack {
    private List<Integer> _stack, _helper;
    private int topIndex = -1;
    /** initialize your data structure here. */
    public MinStack() {
        _stack = new ArrayList();
        _helper = new ArrayList();
    }   
    
    public void push(int x) {
        _stack.add(x);
        if(topIndex == -1 || _helper.get(topIndex) >= x) {
            _helper.add(x);
        }
        else {
            _helper.add(_helper.get(topIndex));
        }
        topIndex++;
    }
    
    public void pop() {
        _stack.remove(topIndex);
        _helper.remove(topIndex);
        topIndex--;
    }
    
    public int top() {
        if(topIndex > -1) {
            return _stack.get(topIndex);
        }
        throw new RuntimeException("栈中元素为空");
    }
    
    public int getMin() {
        if(topIndex > -1) {
            return _helper.get(topIndex);
        }
        throw new RuntimeException("栈中元素为空");
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */