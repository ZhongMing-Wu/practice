class Solution {
    public String minRemoveToMakeValid(String s) {
        StringBuilder sb = new StringBuilder(s);
        int leftBracket = 0;
        for(int i = 0; i < sb.length(); ++i) {
            if(sb.charAt(i) == '(') {
                ++leftBracket;
            } else if(sb.charAt(i) == ')') {
                if(leftBracket > 0) {
                    --leftBracket;
                } else {
                    sb.setCharAt(i, '*');
                }
            }
        }
        int rightBracket = 0;
        for(int i = sb.length() - 1; i >= 0; --i) {
            if(sb.charAt(i) == ')') {
                ++rightBracket;
            } else if(sb.charAt(i) == '(') {
                if(rightBracket > 0) {
                    --rightBracket;
                } else {
                    sb.setCharAt(i, '*');
                }
            }
        }

        StringBuilder ans = new StringBuilder();
        for(int i = 0; i < sb.length(); ++i) {
            if(sb.charAt(i) != '*') {
                ans.append(sb.charAt(i));
            }
        }
        return ans.toString();
    }
}