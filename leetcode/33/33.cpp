class Solution {
public:
    int search(vector<int>& nums, int target) {
        if(nums.size() <= 2) {
            for(int i = 0; i < nums.size(); i++) {
                if(nums[i] == target)
                    return i;
            }
            return -1;
        }
        int left = 0, right = nums.size() - 1;
        int middle, reverseIndex;
        while(1) {
            middle = (left + right) / 2;
            if(middle == 0) {
                reverseIndex = nums[0] > nums[1] ? 1 : 0;
                break;
            }
            if(nums[middle] > nums[middle-1] && nums[middle] < nums[middle+1]) {
                if(nums[middle] < nums[right]) {
                    right = middle - 1;
                }
                else {
                    left = middle + 1;
                }
            }
            else {
                if(nums[middle] > nums[middle-1] && nums[middle] > nums[middle+1])
                    reverseIndex = middle + 1;
                else
                    reverseIndex = middle;
                break;
            }
        }
        if(reverseIndex == 0)
                return searchInOrderArr(nums, 0, nums.size()-1, target);
            else if(target > nums[nums.size()-1])
                return searchInOrderArr(nums, 0, reverseIndex-1, target);
            else
                return searchInOrderArr(nums, reverseIndex, nums.size()-1, target);
    }
    
    int searchInOrderArr(vector<int>& nums, int left, int right, int target) {
        int middle;
        while(left <= right) {
            middle = (left + right)/2;
            if(nums[middle] == target) 
                return middle;
            else if(nums[middle] > target) {
                right = middle - 1;
            }
            else {
                left = middle + 1;
            }
        }
        return -1;
    } 
};