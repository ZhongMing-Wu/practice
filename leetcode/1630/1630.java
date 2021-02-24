class Solution {
    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        int n = l.length;
        HashMap<Integer, Integer> existed = new HashMap<>();
        List<Boolean> ans = new ArrayList<>(n);
        for(int i = 0; i < n; ++i) {
            existed.clear();
            int left = l[i], right = r[i];
            int minVal = nums[left], maxVal = nums[left];
            for(int j = left; j <= right; ++j) {
                minVal = Math.min(nums[j], minVal);
                maxVal = Math.max(nums[j], maxVal);
                existed.put(nums[j], 1);
            }

            if(minVal == maxVal) {
                ans.add(true);
                continue;
            }
            if((maxVal - minVal) % (right - left) != 0) {
                ans.add(false);
                continue;
            }

            int gap = (maxVal - minVal) / (right - left);
            while(minVal + gap <= maxVal) {
                minVal += gap;
                if(!existed.containsKey(minVal)) {
                    ans.add(false);
                    break;
                }
            }
            if(minVal == maxVal) {
                ans.add(true);
            }
        }
        return ans;
    }
}