class Solution {
public:
    int firstMissingPositive(vector<int>& nums) {
        for(int i = 0; i < nums.size(); i++) {
            if(nums[i] < 0) 
                nums[i] = 0;
        }
        int location;
        for(int i = 0; i < nums.size(); i++) {
            if(nums[i] <= 0) {
                continue;
            }
            location = nums[i] - 1;
            if(location >= nums.size()) {
                continue;
            }
            if(location == i) {
                nums[i] = -1;
            }
            else if(nums[location] >= 0) {
                swap(nums[location], nums[i]);
                nums[location] = -1;
                i--;
            }
        }
        
        for(int i = 0; i < nums.size(); i++) {
            if(nums[i] >= 0)
                return i+1;
        }
        return nums.size()+1;
    }
};