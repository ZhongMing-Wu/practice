class Solution {
    public String largestMerge(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();
        int i = 0, j = 0;
        StringBuilder sb = new StringBuilder();
        while(i < len1 || j < len2) {
            if(word1.substring(i).compareTo(word2.substring(j)) > 0) {
                sb.append(word1.charAt(i++));
                continue;
            }
            sb.append(word2.charAt(j++));
        }
        return sb.toString();
    }
}