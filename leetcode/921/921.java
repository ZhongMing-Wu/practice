class Solution {
    public int minAddToMakeValid(String S) {
        int ans = 0, leftCount = 0, rightCount = 0;

        for(int i = 0; i < S.length(); ++i) {
            if(S.charAt(i) == '(') {
                if(rightCount < 0) {
                    ans -= rightCount;
                    rightCount = 0;
                }
                ++leftCount;
            } else {
                if(leftCount > 0) {
                    --leftCount;
                } else {
                    --rightCount;
                }
            }
        }
        if(leftCount > 0) {
            ans += leftCount;
        }

        if(rightCount < 0) {
            ans -= rightCount;
        }

        return ans;
    }
}