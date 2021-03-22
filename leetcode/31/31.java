class Solution {
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        Deque<Integer> stack = new LinkedList<>();
        int[] leftFirstSmallerNumIndex = new int[n];
        for(int i = n - 1; i >= 0; --i) {
            while(!stack.isEmpty() && nums[stack.peekLast()] > nums[i]) {
                int index = stack.pollLast();
                leftFirstSmallerNumIndex[index] = i;
            }
            stack.offerLast(i);
        }
        while(!stack.isEmpty()) {
            int index = stack.pollLast();
            leftFirstSmallerNumIndex[index] = -1;
        }

        int curLeftExchangeLoc = -1, curRightExchangeLoc = n -1;
        for(int i = n - 1; i >= 0; --i) {
            if(i == curLeftExchangeLoc) {
                break;
            }
            int leftSmallerNumIndex = leftFirstSmallerNumIndex[i];
            if(leftSmallerNumIndex > curLeftExchangeLoc) {
                curLeftExchangeLoc = leftSmallerNumIndex;
                curRightExchangeLoc = i;
                continue;
            }
            if(leftSmallerNumIndex == curLeftExchangeLoc) {
                if(nums[i] == nums[curRightExchangeLoc]) {
                    curRightExchangeLoc = i;
                } else{
                    curRightExchangeLoc = nums[i] > nums[curRightExchangeLoc] ? curRightExchangeLoc : i;
                }
            }
        }
        if(curLeftExchangeLoc == -1) {
            exchangeArray(nums, 0, n - 1);
            return;
        }
        int num = nums[curLeftExchangeLoc];
        nums[curLeftExchangeLoc] = nums[curRightExchangeLoc];
        nums[curRightExchangeLoc] = num;
        Arrays.sort(nums, curLeftExchangeLoc + 1, n);
    }

    private void exchangeArray(int[] nums, int left, int right) {
        for(int i = left, j = right; i < j; ++i, --j) {
            int num = nums[i];
            nums[i] = nums[j];
            nums[j] = num;
        }
    }
}