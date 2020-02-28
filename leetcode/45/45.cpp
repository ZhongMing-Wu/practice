class Solution {
public:
    int jump(vector<int>& nums) {
        if(nums.size() == 1)
            return 0;
        int end = nums.size() - 1;
        int currentLoc = 0;
        int step = 0;
        int nextLoc = 1;
        while(currentLoc + nums[currentLoc] < end && nums[currentLoc] != 0  ) {
            for(int i = 1; i <= nums[currentLoc]; i++) {        
                if(currentLoc + i + nums[currentLoc + i] > nextLoc + nums[nextLoc] && nums[currentLoc + i] != 0) {
                    nextLoc = currentLoc + i;
                }
            }
            currentLoc = nextLoc;
            step++;
            nextLoc++;
        }
        step++;
        return step;
    }
};