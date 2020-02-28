class Solution {
public:
    int longestValidParentheses(string s) {
        int maxLength = 0;
        int leftCount = 0, rightCount = 0;
        for(int i = 0; i < s.size(); i++) {
            if(s[i] == '(') {
                leftCount++;
            }
            else {
                rightCount++;
                if(leftCount == rightCount) {
                    maxLength = max(maxLength, rightCount*2);
                }
                else if(rightCount > leftCount) {
                    leftCount = rightCount = 0;
                }
            }
        }
        
        rightCount = leftCount = 0;
        for(int i = s.size()-1; i >= 0; i--) {
            if(s[i] == ')') {
                rightCount++;
            }
            else {
                leftCount++;
                if(leftCount == rightCount) {
                    maxLength = max(maxLength, rightCount*2);
                }
                else if(leftCount > rightCount) {
                    leftCount = rightCount = 0;
                }
            }
        }
        return maxLength;
    }
};