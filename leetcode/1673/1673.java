class Solution {
    public int[] mostCompetitive(int[] nums, int k) {
        Deque<Integer> deque = new LinkedList<>();
        List<Integer> list = new ArrayList<>(k);
        for(int i = 0; i < nums.length; i++) {
            while(!deque.isEmpty() && deque.peekLast() > nums[i]) {
                deque.pollLast();
            }
            deque.add(nums[i]);
            if(k - list.size() > nums.length - i - 1) {
                int temp = deque.pollFirst();
                list.add(temp);
            }
        }
        int[] ans = new int[k];
        for(int i = 0; i < k; i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }
}