class Solution {
public:
    int longestConsecutive(vector<int>& nums) {
        unordered_map<int, int> existed;
        for(int i = 0; i < nums.size(); i++) {
            existed[nums[i]] = 1;
        }
        int maxLen = 0, currentLen, currentNum;
        for(int i = 0; i < nums.size(); i++) {
            currentNum = nums[i];
            currentLen = 0;
            if(!existed[currentNum - 1]) {
                while(existed[currentNum]) {
                    currentNum++;
                    currentLen++;
                }
            }
            maxLen = max(maxLen, currentLen);
        }
        return maxLen;
    }
};