class Solution {
public:
    int threeSumClosest(vector<int>& nums, int target) {
        sort(nums.begin(),nums.end());
        int ans = 99999999;
        int left,right;
        for(int i = 0;i < nums.size()-1;i++) {
            left = 0;
            right = nums.size()-1;
            /* if(nums[i] == nums[i+1] && i != nums.size()-2)
                i++; */
            while(left < i && right > i) {
                if(nums[left]+nums[i]+nums[right] == target) {
                    return target;
                }
                if(abs(nums[left]+nums[i]+nums[right]-target) < abs(ans-target)) {
                    ans = nums[left]+nums[i]+nums[right];
                }
                if(nums[left]+nums[i]+nums[right]<target) {
                    while(left < i && nums[left] == nums[left+1])
                        left++;
                    left++;
                }
                else {
                    while(right > i&& nums[right] == nums[right-1]) 
                        right--;
                    right--;
                }
            }
            if(i-2 >= 0 && nums[i] == nums[i-1] && nums[i] == nums[i-2])
                while(i < nums.size()-1 && nums[i] == nums[i+1])
                    i++;
        }
        return ans;
    }
};