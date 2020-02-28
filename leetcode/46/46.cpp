class Solution {
public:
    vector<vector<int>> permute(vector<int>& nums) {
        vector<int> oneAns;
        DFS(nums, oneAns, 0);
        return allAns;
    }
    void DFS(vector<int>& nums, vector<int>& oneAns, int start) {
        for(int i = start; i < nums.size(); i++){
            oneAns.push_back(nums[i]);
            if(oneAns.size() == nums.size()) {
                allAns.push_back(oneAns);
                oneAns.pop_back();
                return;
            }
            else {
                swap(nums[i], nums[start]);
                DFS(nums, oneAns, start+1);
                oneAns.pop_back();
                swap(nums[i], nums[start]);
            }
        }
    }
private:
    vector<vector<int>> allAns;
};