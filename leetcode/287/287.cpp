class Solution {
    public int findDuplicate(int[] nums) {
        int fast = 0, slow = 0;
        do {
            fast =  nums[nums[fast]];
            slow = nums[slow];
        } while(fast != slow);

        fast = 0;
        while(nums[fast] != nums[slow]) {
            fast = nums[fast];
            slow = nums[slow];
        }
        return nums[fast];
    }
}