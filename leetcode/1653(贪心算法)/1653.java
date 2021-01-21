class Solution {
    public int minimumDeletions(String s) {
        int n = s.length();
        int[] preA = new int[n];
        int[] postB = new int[n];
        for(int i = 0, j = n - 1; i < n; ++i, --j) {
            if(i == 0) {
                preA[0] = s.charAt(0) == 'a' ? 1 : 0;
                postB[n - 1] = s.charAt(n - 1) == 'b' ? 1 : 0;
                continue;
            }

            preA[i] = preA[i - 1] + (s.charAt(i) == 'a' ? 1 : 0);
            postB[j] = postB[j + 1] + (s.charAt(j) == 'b' ? 1 : 0); 
        }

        int leftMax = 0;
        for(int i = 0; i < n; ++i) {
            leftMax = Math.max(leftMax, preA[i] + postB[i]);
        }
        return n - leftMax;
    }
}