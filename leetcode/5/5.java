class Solution {
    public String longestPalindrome(String s) {
        if(s.length() < 2) {
            return s;
        }
        StringBuilder sb = new StringBuilder(s.length() * 2 + 1);
        sb.append('#');
        for(int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i));
            sb.append('#');
        }
        String str = sb.toString();
        int[] rightLength = new int[str.length()];
        int rightBoundary = -1, curCenter = 0;
        for(int i = 0; i < str.length(); i++) {
            if(i <= rightBoundary) {
                if(rightLength[2 * curCenter - i] >= rightBoundary - i) {
                    rightLength[i] = getRightLength(str, 2 * i - rightBoundary - 1, rightBoundary + 1);
                    curCenter = i;
                    rightBoundary = i + rightLength[i];
                }
            } else {
                rightLength[i] = getRightLength(str, i - 1, i + 1);
                curCenter = i;
                rightBoundary = i + rightLength[i];
            }
        }

        int maxRightLenIndex = 0;
        for(int i = 0; i < rightLength.length; i++) {
            if(rightLength[i] > rightLength[maxRightLenIndex]) {
                maxRightLenIndex = i;
            }
        }
        StringBuilder ans = new StringBuilder(rightLength[maxRightLenIndex] + 1);
        for(int i = maxRightLenIndex - rightLength[maxRightLenIndex]; i < maxRightLenIndex + rightLength[maxRightLenIndex]; i++) {
            if(str.charAt(i) != '#') {
                ans.append(str.charAt(i));
            }
        }

        return ans.toString();
    }

    private int getRightLength(String str, int left, int right) {
        while(left >= 0 && right < str.length() && str.charAt(left) == str.charAt(right)) {
            left--;
            right++;
        }
        return (right - left) / 2 - 1;
    }
}