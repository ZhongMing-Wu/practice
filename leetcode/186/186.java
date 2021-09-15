class Solution {
    public void reverseWords(char[] s) {
        int n = s.length, start = 0;
        while(start < n) {
            int end = findEndIndexOfWord(s, start);
            reverseWord(s, start, end);
            start = end + 2;
        }
        reverseWord(s, 0, n - 1);
    }

    public void reverseWord(char[] s, int begin, int end) {
        while(begin < end) {
            char temp = s[begin];
            s[begin] = s[end];
            s[end] = temp;
            begin++;
            end--;
        }
    }

    public int findEndIndexOfWord(char[] s, int start) {
        while(start + 1 < s.length && s[start + 1] != ' ') {
            start++;
        }
        return start;
    }
}