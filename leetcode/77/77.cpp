class Solution {
public:
    vector<vector<int>> combine(int n, int k) {
        vector<int> oneAns;
        findOneAns(oneAns, 1, n, k);
        return ans;
    }

    void findOneAns(vector<int>& oneAns, int start, int n, int k) {
        for(; start <= n - k + oneAns.size() + 1; start++) {
            oneAns.push_back(start);
            if(oneAns.size() == k) {
                ans.push_back(oneAns);
                oneAns.pop_back();
            }
            else {
                findOneAns(oneAns, start + 1, n, k);
                oneAns.pop_back();
            }
        }
    }
private:
    vector<vector<int>> ans;
};