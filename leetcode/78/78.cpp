class Solution {
public:
    vector<vector<int>> subsets(vector<int>& nums) {
        vector<int> oneAns;
        ans.push_back(oneAns);
        for(int i = 1; i <= nums.size(); i++) {
            findOneAns(oneAns, 0, nums, i);
        }
        return ans;
    }

    void findOneAns(vector<int>& oneAns, int start, vector<int>& nums, int k) {
		for (; start <= nums.size() - k + oneAns.size(); start++) {
			oneAns.push_back(nums[start]);
			if (oneAns.size() == k) {
				ans.push_back(oneAns);
				oneAns.pop_back();
			}
			else {
				findOneAns(oneAns, start + 1, nums, k);
				oneAns.pop_back();
			}
		}
	}
private:
    vector<vector<int>> ans;
};