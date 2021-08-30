class Solution {
    public String longestCommonPrefix(String[] strs) {
        String curPrefix = strs[0];
        for(String str : strs) {
            curPrefix = commonPrefix(curPrefix, str);
        }
        return curPrefix;
    }

    public String commonPrefix(String s1, String s2) {
        int curIndex = 0, len = Math.min(s1.length(), s2.length());
        for(;curIndex < len; curIndex++) {
            if(s1.charAt(curIndex) != s2.charAt(curIndex)) {
                return s1.substring(0, curIndex);
            }
        }
        return s1.substring(0, curIndex);
    }
}