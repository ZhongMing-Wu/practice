class FreqStack {
    int maxFreq;
    HashMap<Integer, Integer> toFreq;
    HashMap<Integer, Deque<Integer>> toElementQueue;
    public FreqStack() {
        maxFreq = 0;
        toFreq = new HashMap<>();
        toElementQueue = new HashMap<>();
    }
    
    public void push(int val) {
        int freq = toFreq.getOrDefault(val, 0);
        freq++;
        toFreq.put(val, freq);
        maxFreq = Math.max(maxFreq, freq);
        if(!toElementQueue.containsKey(freq)) {
            toElementQueue.put(freq, new LinkedList<>());
        }

        Deque<Integer> stack = toElementQueue.get(freq);
        stack.offerLast(val);
    }
    
    public int pop() {
        Deque<Integer> stack = toElementQueue.getOrDefault(maxFreq, null);
        if(stack == null || stack.size() == 0) {
            return -1;
        }

        int val = stack.pollLast();
        toFreq.put(val, toFreq.get(val) - 1);
        if(stack.size() == 0) {
            maxFreq--;
        }
        return val;
    }
}

/**
 * Your FreqStack object will be instantiated and called as such:
 * FreqStack obj = new FreqStack();
 * obj.push(val);
 * int param_2 = obj.pop();
 */