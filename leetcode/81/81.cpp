class Solution {
public:
    bool search(vector<int>& nums, int target) {
        if(!nums.size())   
            return false;
        int left = 0, right = nums.size() - 1;
        int middle;
        while(left <= right) {
            middle = (left + right) / 2;
            if(nums[middle] == target) {
                return true;
            }
            else {
                if(nums[left] < nums[middle]) {
                    if(target > nums[middle] || target < nums[left]) {
                        left = middle + 1;
                    }
                    else {
                        right = middle - 1;
                    }
                }
                else if(nums[left] > nums[middle]){
                    if(target < nums[middle] || target > nums[right]) {
                        right = middle - 1;
                    }
                    else {
                        left = middle + 1;
                    }
                }
                else {
                    left++;
                }
            }
        }
        return false;
    }
};