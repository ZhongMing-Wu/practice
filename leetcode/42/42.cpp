class Solution {
public:
    int trap(vector<int>& height) {
        if(height.size() == 0)
            return 0;
        int ans = 0;
        vector<int> leftMax;
        vector<int> rightMax;
        leftMax.resize(height.size());
        rightMax.resize(height.size());
        leftMax[0] = height[0];
        for(int i = 1; i < height.size() - 1; i++) {
            leftMax[i] = max(height[i], leftMax[i - 1]);
        }
        
        rightMax[height.size() - 1] = height[height.size() - 1];
        for(int i = height.size() - 2; i > 0; i--) {
            rightMax[i] = max(height[i], rightMax[i+1]);
        }
        for(int i = 1; i < height.size() - 1; i++) {
            ans += min(rightMax[i], leftMax[i]) - height[i];
        }
        return ans;
    }
};