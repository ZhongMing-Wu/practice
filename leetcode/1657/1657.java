class Solution {
    public boolean closeStrings(String word1, String word2) {
        if(word1.length() != word2.length()) {
            return false;
        }
        int[] occurTimes1 = new int[26];
        int[] occurTimes2 = new int[26];
        for(int i = 0; i < word1.length(); ++i) {
            ++occurTimes1[word1.charAt(i) - 'a'];
            ++occurTimes2[word2.charAt(i) - 'a'];
        }

        for(int i = 0; i < 26; ++i) {
            if(occurTimes1[i] == 0 && occurTimes2[i] == 0) {
                continue;
            }

            if(occurTimes1[i] != 0 && occurTimes2[i] != 0) {
                continue;
            }
            return false;
        }

        Arrays.sort(occurTimes1);
        Arrays.sort(occurTimes2);

        for(int i = 0; i < 26; ++i) {
            if(occurTimes1[i] != occurTimes2[i]) {
                return false;
            }
        } 
        return true;
    }
}