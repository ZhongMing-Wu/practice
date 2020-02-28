class Solution {
public:
    vector<vector<int>> subsetsWithDup(vector<int>& nums) {
        if(!nums.size())
            return anses;
        sort(nums.begin(), nums.end());
        vector<int> oneAns;
        anses.push_back(oneAns);
        for(int i = 1; i < nums.size(); i++) {
            findOneAns(nums, 0, oneAns, calculateCount(0, nums), i);
        }
        anses.push_back(nums);
        return anses;
    }

    int calculateCount(int start, vector<int>& nums) { //用于查找元素nums[start]在nums中的个数
        if(start >= nums.size()) 
            return 0;
        int num = 0, val = nums[start];
        while(start < nums.size() && nums[start] == val) {
            start++;
            num++;
        }
        return num;
    }

    void findOneAns(vector<int>& nums, int start, vector<int>& oneAns, int num, int len) {
        if(start >= nums.size()) {
            return;
        }
        for(int i = 0; i <= num; i++) {
            if(oneAns.size() + i <= len) {
                for(int j = 0; j < i; j++) {
                    oneAns.push_back(nums[start]);
                }

                if(oneAns.size() == len) {
                    anses.push_back(oneAns);
                }
                else {
                    findOneAns(nums, start + num, oneAns, calculateCount(start + num, nums), len);
                }

                for(int j = 0; j < i; j++) {
                    oneAns.pop_back();
                }
            }
        }

    }
private:
    vector<vector<int>> anses;
};