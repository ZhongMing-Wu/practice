class SummaryRanges {

    /** Initialize your data structure here. */
    TreeSet<Integer> set;
    public SummaryRanges() {
        set = new TreeSet<>();
    }
    
    public void addNum(int val) {
        set.add(val);
    }
    
    public int[][] getIntervals() {
        if(set.size() == 0) {
            return new int[0][0];
        }
        List<int[]> list = new ArrayList<>();
        int start = set.first(), end = set.first();
        for(int num : set) {
            if(end == num) {
                continue;
            }
            if(num - end == 1) {
                end = num;
                continue;
            }
            int[] arr = new int[2];
            arr[0] = start;
            arr[1] = end;
            list.add(arr);
            start = num;
            end = num;
        }
        int[] arr = new int[2];
        arr[0] = start;
        arr[1] = end;
        list.add(arr);
        int[][] ans = new int[1][1];
        return list.toArray(ans);
    }
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(val);
 * int[][] param_2 = obj.getIntervals();
 */