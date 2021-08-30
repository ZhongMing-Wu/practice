class Solution {
    public int compress(char[] chars) {
        int start = 0, len = chars.length, modifyIndex = 0;
        while(start < len) {
            int commonLen = findCommonLen(chars, start);
            int nextStart = start + commonLen;
            chars[modifyIndex++] = chars[start];
            int begin = modifyIndex;
            if(commonLen > 1) {
                while(commonLen != 0) {
                    chars[modifyIndex++] = (char)(commonLen % 10 + 48);
                    commonLen /= 10;
                }
                reverseChars(chars, begin, modifyIndex - 1);
            }
            start = nextStart;
        }
        return modifyIndex;
    }

    public void reverseChars(char[] chars, int begin, int end) {
        while(begin < end) {
            char c = chars[begin];
            chars[begin] = chars[end];
            chars[end] = c;
            begin++;
            end--;
        }
    }
    public int findCommonLen(char[] chars, int start) {
        int curIndex = start, len = chars.length;
        while(curIndex < len && chars[start] == chars[curIndex]) {
            curIndex++;
        }
        return curIndex - start;
    }
}