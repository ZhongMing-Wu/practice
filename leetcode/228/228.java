class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> ans = new ArrayList<>(nums.length);
        if(0 == nums.length) {
            return ans;
        }
        int start = nums[0];
        int end = nums[0];

        for(int i = 1; i < nums.length; i++) {
            if(nums[i] - end != 1) {
                addOneAns(ans, start, end);
                start = end = nums[i];
            } else {
                end = nums[i];
            }
        }
        addOneAns(ans, start, end);
        return ans;
    }

    void addOneAns(List<String> ans, int start, int end) {
        StringBuilder sb = new StringBuilder();
        if(start == end) {
            sb.append(start);
        } else {
            sb.append(start).append("->").append(end);
        }
        ans.add(sb.toString());
    }
}