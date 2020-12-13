class MedianFinder {

    PriorityQueue<Integer> minHeap, maxHeap;
    int total;
    /** initialize your data structure here. */
    public MedianFinder() {
        total = 0;
        minHeap = new PriorityQueue<Integer>();
        maxHeap = new PriorityQueue<Integer>((a, b) -> b - a);
    }
    
    public void addNum(int num) {
        total++;
        if(total % 2 == 1) {
            if(0 == maxHeap.size() || num >= maxHeap.peek()) {
                minHeap.offer(num);
            } else {
                maxHeap.offer(num);
                minHeap.offer(maxHeap.poll());
            }
        } else {
            minHeap.offer(num);
            maxHeap.offer(minHeap.poll());
        }
    }
    
    public double findMedian() {
        if(total % 2 == 1) {
            return (double) minHeap.peek();
        }

        return (minHeap.peek() + maxHeap.peek()) / 2.0;
    }
}