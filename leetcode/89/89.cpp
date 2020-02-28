class Solution {
public:
    vector<int> grayCode(int n) {
        vector<int> ans;
        ans.push_back(0);
        if(n == 0)
            return ans;
        else
            ans.push_back(1);
        for(int i = 2; i <= n; i++) {
            for(int j = 0; j < ans.size(); j++) {
                ans[j] *= 2;
            }

            for(int j = ans.size() -1; j >= 0; j--) {
                ans.push_back(ans[j] + 1);
            }
        }
        return ans;
    }
};