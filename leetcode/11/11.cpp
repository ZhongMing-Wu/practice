class Solution {
public:
    int maxArea(vector<int>& height) {
        int left=0,right=height.size()-1;
        int ans=0;
        int minVal;
        while(left<right){
            minVal=min(height[left],height[right]);
            ans=max(ans,minVal*(right-left));
            if(height[left]>height[right])
                right--;
            else
                left++;
        }
        return ans;
    }
};