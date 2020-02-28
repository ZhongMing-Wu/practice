class Solution {
public:
    vector<vector<int>> insert(vector<vector<int>>& intervals, vector<int>& newInterval) {
        vector<vector<int>> newAns;
        int i;
        int left = newInterval[0], right = newInterval[1];
        for(i = 0; i < intervals.size(); i++) {
            if(left <= intervals[i][1]) {
                left = min(left, intervals[i][0]);
                break;
            }
            else {
                newAns.push_back(intervals[i]);
            }
        }
        for( ; i < intervals.size(); i++) {
            if(right < intervals[i][0]) {
                newAns.emplace_back(vector<int> {left, right});
                break;
            }
            else {
                right = max(right, intervals[i][1]);
            }
        }
        if(i == intervals.size())
            newAns.emplace_back(vector<int> {left, right});
        for( ; i < intervals.size(); i++)
            newAns.push_back(intervals[i]);
        return newAns;
    }
};