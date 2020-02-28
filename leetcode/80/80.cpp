class Solution {
public:
    int removeDuplicates(vector<int>& nums) {
        if(nums.size() <= 2)
            return nums.size();
        int currentVal = nums[0];
        int i  = 0, j = 0;  //通过i,j 对数组nums进行遍历
        int occurCount = 0;  //记录某个element出现的次数
        while(j < nums.size()) {
            if(nums[j] == currentVal) {
                if(occurCount < 2) {
                    nums[i] = nums[j];
                    i++;
                    j++;
                    occurCount++;
                }
                else {
                    j++;
                }
            }
            else {
                nums[i] = nums[j];
                currentVal = nums[j];
                occurCount = 1;
                i++;
                j++;
            }
        }
        return i;
    }
};