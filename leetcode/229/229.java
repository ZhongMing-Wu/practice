class Solution {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        if(0 == nums.length) {
            return ans;
        }

        int threshHold = nums.length / 3;
        int first, firstCount = 0, second, secondCount = 0;
        
        first = nums[0];
        firstCount++;

        int i = 1;
        while(i < nums.length && nums[i] == first) {
            i++;
            firstCount++;
        } 

        if(i == nums.length) {
            ans.add(first);
            return ans;
        }

        second = nums[i++];
        secondCount++;
        
        while(i < nums.length) {
            if(first == nums[i]) {
                firstCount++;
                i++;
                continue;
            }
            
            if(second == nums[i]) {
                secondCount++;
                i++;
                continue;
            } 
            
            if(firstCount == 0) {
                first = nums[i];
                firstCount = 1;
                i++;
                continue;
            }

            if(secondCount == 0) {
                second = nums[i];
                secondCount = 1;
                i++;
                continue;
            }

            firstCount--;
            secondCount--;
            i++;
        }

        firstCount = 0;
        secondCount = 0;
        for(i = 0; i < nums.length; i++) {
            if(nums[i] == second) {
                secondCount++;
            }
            if(nums[i] == first) {
                firstCount++;
            }
        }

        if(firstCount > threshHold) {
            ans.add(first);
        }

        if(secondCount > threshHold) {
            ans.add(second);
        }

        return ans;
    }
}