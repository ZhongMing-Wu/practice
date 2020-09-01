class Solution {
    public String shortestPalindrome(String s) {
        if(s.length() == 0) {
            return "";
        }
        StringBuilder str1 = new StringBuilder(s);
        StringBuilder str2 = new StringBuilder();
        str1.reverse();
        str2.append(s);
        str2.append("#");
        str2.append(str1);
        str2.append("$");
        int len = str2.length();
        int[] next = new int[len];
        next[0] = -1;
        int i = 0, j = -1;
        while(i < len - 1) {
            if(j == -1 || str2.charAt(i) == str2.charAt(j)) {
                i++;
                j++;
                next[i] = j;
            }
            else {
                j = next[j];
            }
        }
        StringBuilder str3 = new StringBuilder(s.substring(next[len - 1]));
        str3.reverse();
        str3.append(s);
        return str3.toString();
    }
}