class Solution {
public:
    bool canJump(vector<int>& nums) {
        if(nums.size() <= 1)
            return true;
        int currentLoc = 0;
        int nextLoc = 0;
        int flag = 0;
        while(1) {
            if(currentLoc + nums[currentLoc] >= nums.size() - 1) {
                flag = 1;
                break;
            }
            for(int i = 0; i <= nums[currentLoc]; i++) {
                if(nextLoc - currentLoc + nums[nextLoc] < i + nums[currentLoc + i] && 
                nums[currentLoc + i] != 0) {
                    nextLoc = currentLoc + i;
                }
            }
            if(currentLoc != nextLoc)
                currentLoc = nextLoc;
            else {
                flag = 0;
                break;
            }
        }
        if(flag)
            return true;
        else
            return false;
    }
};