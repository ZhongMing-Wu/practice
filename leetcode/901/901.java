class StockSpanner {
    Deque<Integer> decreasingPrice, span;
    public StockSpanner() {
        decreasingPrice = new LinkedList<>();
        span = new LinkedList<>();
    }
    
    public int next(int price) { 
        if(decreasingPrice.isEmpty()) {
            decreasingPrice.offerLast(price);
            span.offerLast(1);
            return 1;
        }
        int spanSum = 1;
        while(!decreasingPrice.isEmpty() && decreasingPrice.peekLast() <= price) {
            spanSum += span.pollLast();
            decreasingPrice.pollLast();
        }
        decreasingPrice.offerLast(price);
        span.offerLast(spanSum);
        return spanSum;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */