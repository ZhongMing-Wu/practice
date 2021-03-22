class Solution {
    public String largestMerge(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();
        if(len1 == 0) {
            return word2;
        }      
        if(len2 == 0) {
            return word1;
        }
        StringBuilder sb = new StringBuilder();
        int index1 = 0, index2 = 0;
        while(index1 < len1 && index2 < len2) {
            int end1 = findIncrEnd(word1, index1);
            int end2 = findIncrEnd(word2, index2);

            if(word1.substring(index1, end1).compareTo(word2.substring(index2, end2)) > 0) {
                sb.append(word1.substring(index1, end1));
                index1 = end1;
                continue;
            }
            if(word1.substring(index1, end1).compareTo(word2.substring(index2, end2)) < 0) {
                sb.append(word2.substring(index2, end2));
                index2 = end2;
                continue;
            }
            if(end1 != len1 && end2 != len2) {
                int start1 = end1, start2 = end2;
                while(start1 < len1 && start2 < len2 && word1.charAt(start1) == word2.charAt(start2)) {
                    ++start1;
                    ++start2;
                }
                if(start1 == len1 || word1.charAt(start1) < word2.charAt(start2)) {
                    sb.append(word2.substring(index2, end2));
                    index2 = end2;
                    continue;
                }
                if(start2 == len2 || word1.charAt(start1) > word2.charAt(start2)) {
                    sb.append(word1.substring(index1, end1));
                    index1 = end1;
                    continue;
                }
            }
            if(end1 == len1 || end2 == len2) {
                String s1 = word1.substring(index1, len1) + word2.substring(index2, len2);
                String s2 = word2.substring(index2, len2) + word1.substring(index1, len1);
                if(s1.compareTo(s2) >= 0) {
                    sb.append(s1);
                } else {
                    sb.append(s2);
                }
                index1 = len1;
                index2 = len2;
            }
        }
        if(index1 != len1) {
            sb.append(word1.substring(index1, len1));
        }
        if(index2 != len2) {
            sb.append(word2.substring(index2, len2));
        }
        return sb.toString();
    }
    // 找递增序列的末尾位置
    private int findIncrEnd(String word, int start) {
        char preChar = 'a';
        int index = start;
        while(index < word.length() && word.charAt(index) >= preChar) {
            preChar = word.charAt(index);
            ++index;
        }
        return index;
    }
}