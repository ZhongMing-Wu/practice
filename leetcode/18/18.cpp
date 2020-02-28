class Solution {
public:
    vector<vector<int>> fourSum(vector<int>& nums, int target) {
        vector<vector<int>> ans;
        if(nums.size() < 4)
            return ans;
        sort(nums.begin() ,nums.end());
        int left,right;
        for(int i = 0;i < nums.size()-3;i++) {
            for(int j = i+1;j < nums.size()-2;j++) {
                left = j+1;
                right = nums.size()-1;
                while(left<right) {
                    if(nums[i] + nums[j] + nums[left] + nums[right] == target){
                        ans.push_back({nums[i] ,nums[j] ,nums[left] ,nums[right]});  //将符合条件的组合添加到ans中
                        while (right - 1 > left && nums[right] == nums[right - 1])
							right--;
						right--;
						while (left + 1 < right && nums[left] == nums[left + 1])
							left++;
						left++;
                    }
                    else if(nums[i] + nums[j] + nums[left] + nums[right] > target) {
                        while(right-1 > left && nums[right] == nums[right-1])
                            right--;
                        right--;
                    }
                    else {
                        while(left+1 < right && nums[left] == nums[left+1])
                            left++;
                        left++;
                    }
                }
                while(j < nums.size()-2 && nums[j] == nums[j+1])
                    j++;
            }
            while(i < nums.size()-3 && nums[i] == nums[i+1])
                i++;
        }
        return ans;
    }
};