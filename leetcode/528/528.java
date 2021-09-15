class Solution {

    HashMap<Integer, Integer> toWeight;
    int sum = 0;
    Random random;
    List<Integer> list;
    public Solution(int[] w) {
        random = new Random();
        toWeight = new HashMap<>();
        list = new ArrayList<>();
        for(int i = 0; i < w.length; i++) {
            sum += w[i];
            list.add(sum - 1);
            toWeight.put(sum - 1, i);
        }
    }
    
    public int pickIndex() {
        int randomNum = random.nextInt(sum);
        int left = 0, right = list.size() - 1;
        while(left < right) {
            int mid = (right - left) / 2 + left;
            int num = list.get(mid);
            if(num == randomNum) {
                return toWeight.get(randomNum);
            }
            if(num < randomNum) {
                left = mid + 1;
            }
            if(num > randomNum) {
                right = mid;
            }
        }
        return toWeight.get(list.get(right));
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */