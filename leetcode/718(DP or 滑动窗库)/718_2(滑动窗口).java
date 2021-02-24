class Solution {
    public int findLength(int[] A, int[] B) {
        int maxLen = findMaxSubCommonArray(A, B);
        maxLen = Math.max(maxLen, findMaxSubCommonArray(B, A));
        return maxLen;
    }

    private int findMaxSubCommonArray(int[] A, int[] B) {
        int start = B.length - 1;
        int maxLen = 0;
        while(start >= 0) {
            int compareLen = Math.min(B.length - start, A.length);
            int curMaxLen = 0;
            for(int i = 0, j = start; i < compareLen; ++i, ++j) {
                if(A[i] != B[j]) {
                    maxLen = Math.max(maxLen, curMaxLen);
                    curMaxLen = 0;
                    continue;
                }
                ++curMaxLen;
            }
            maxLen = Math.max(maxLen, curMaxLen);
            --start;
        }
        return maxLen;
    }
}