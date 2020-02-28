class Solution {
public:
    vector<vector<int>> combinationSum(vector<int>& candidates, int target) {
        int sum = 0;
        sort(candidates.begin(), candidates.end());
        findAns(candidates, target, 0, 0);
        return ans;
    }
    
    void findAns(const vector<int>& candidates, const int& target, int sum, const int start) {
        for(int i = start; i < candidates.size(); i++) {
            oneAns.push_back(candidates[i]);
            sum += candidates[i];
            if(sum == target){
                ans.push_back(oneAns);
                oneAns.pop_back();
                return;
            }
            else if(sum < target) {
                findAns(candidates, target, sum, i);
                oneAns.pop_back();
                sum -= candidates[i];
            }
            else {
                oneAns.pop_back();
                return;
            }
        }
    }
private:
    vector<int> oneAns;
    vector<vector<int>> ans;
};