class Solution {
public:
    int largestRectangleArea(vector<int>& heights) {
        stack<int> s;
        heights.push_back(INT_MIN);
        int maxVal = 0;
        int i;
        int start = 0;
        for(i = 0; i < heights.size() - 1;) {
            if(heights[i] == 0) {
                if(s.empty()) {
                    i++;
                    start = i;
                    continue;
                }
            }
            if(s.empty() || heights[s.top()] <= heights[i]) {
                s.push(i);
                i++;
            }
            else {
                tackleStack(s, heights, maxVal, i, start);
            }
            
        }
        if(!s.empty()) {
            tackleStack(s, heights, maxVal, i, start);
        }
        return maxVal;
    }

    void tackleStack(stack<int>& s, vector<int>& heights, int& maxVal, int i, int start) {
        int topLocation;
        while(!s.empty() && heights[s.top()] >= heights[i]) {
            topLocation = s.top();
            s.pop();
            if(s.empty()) {
                maxVal = max(maxVal, heights[topLocation] * (i - start));
            }
            else {
                maxVal = max(maxVal, heights[topLocation] * (i - s.top() - 1)); 
            }
        }
    }
};