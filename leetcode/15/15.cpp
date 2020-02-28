class Solution {
public:
    vector<vector<int>> threeSum(vector<int>& nums) {
        vector<vector<int>> ans;
        if(nums.size()==0)
            return ans;
        sort(nums.begin(),nums.end());
        int left,right;
        for(int i=0;i<nums.size()-1;i++) {
            left = 0;
            right = nums.size()-1;
            if(nums[i]<=0&&nums[i]==nums[i+1])
                i++;
            while(left<i&&right>i) {
                if(nums[i]+nums[left]+nums[right]==0) {
                    ans.push_back({nums[left],nums[i],nums[right]});
                    while(right>i&&nums[right]==nums[right-1])
                        right--;
                    while(left<i&&nums[left]==nums[left+1])
                        left++;
                    right--;
                    left++;
                } 
                else if(nums[i]+nums[left]+nums[right]<0) {
                    left++;
                }
                else {
                    right--;
                }
            }
            while(i<(nums.size()-1)&&nums[i]==nums[i+1]) {
                i++;
            }
        }
        return ans;
    }
};