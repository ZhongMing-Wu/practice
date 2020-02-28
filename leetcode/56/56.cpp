class Solution {
public:
    vector<vector<int>> merge(vector<vector<int>>& intervals) {
        if(intervals.size() <= 1)
            return intervals;
        vector<vector<int>> ans;
        vector<int> oneAns;
        sort(intervals.begin(), intervals.end(), cmp);
        int left = intervals[0][0], right = intervals[0][1];
        for(int i = 1; i < intervals.size(); i++) {
            if(right < intervals[i][0]) {
                oneAns.push_back(left);
                oneAns.push_back(right);
                ans.push_back(oneAns);
                left = intervals[i][0];
                right = intervals[i][1];
                oneAns.clear();
            }
            else if(right >= intervals[i][0] && right < intervals[i][1]) {
                right = intervals[i][1];
            }
        }
        oneAns.push_back(left);
        oneAns.push_back(right);
        ans.push_back(oneAns);
        return ans;
    }

    static bool cmp(vector<int>& a, vector<int>& b) {
        return a[0] < b[0];
    }
};