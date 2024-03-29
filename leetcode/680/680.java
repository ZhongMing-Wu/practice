class Solution {
    public boolean validPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while(left < right) {
            if(s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
                continue;
            }
            return secondJudgeValidPalindrome(s, left, right - 1) || secondJudgeValidPalindrome(s, left + 1, right);
        }
        return true;
    }

    public boolean secondJudgeValidPalindrome(String s, int left, int right) {
        while(left < right) {
            if(s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}