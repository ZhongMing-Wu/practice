class Solution {
    public boolean checkValidString(String s) {
        int leftBracketCount = 0, unuseStarCount = 0, starAsRightBracketCoun = 0;
        for(int i = 0; i < s.length(); ++i) {
            if(s.charAt(i) == '(') {
                ++leftBracketCount;
                continue;
            }
            if(s.charAt(i) == '*') {
                if(leftBracketCount > 0) {
                    --leftBracketCount;
                    ++starAsRightBracketCoun;
                } else {
                    ++unuseStarCount;
                }
                continue;
            }
            if(s.charAt(i) == ')') {
                if(leftBracketCount > 0) {
                    --leftBracketCount;
                } else {
                    if(starAsRightBracketCoun > 0) {
                        --starAsRightBracketCoun;
                        ++unuseStarCount;
                    } else if(unuseStarCount > 0) {
                        --unuseStarCount;
                    } else {
                        return false;
                    }
                }
            }
        }
        if(leftBracketCount > 0) {
            return false;
        }
        return true;
    }
}