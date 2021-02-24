class Solution {
    public int numWays(String[] words, String target) {
        int n = words.length, m = target.length(), oneWordLen = words[0].length();
        if(oneWordLen < m) {
            return 0;
        }

        // 记录每一列某个字符出现的次数。
        int [][] occurTime = new int[oneWordLen][26];
        for(int i = 0; i < oneWordLen; ++i) {
            for(int j = 0; j < n; ++j) {
                ++occurTime[i][words[j].charAt(i) - 'a'];
            }
        }
        // dp1[i][j] 表示 target 前 i 个元素在 words 的前 j 列中构造方案数
        long[][] dp1 = new long[m + 1][oneWordLen + 1];
        for(int i = 1; i <= m; ++i) {
            for(int j = i; j <= oneWordLen - m + i; ++j) {
                // 计算 target 前 i 个元素在 words 的前 j 列中，并且第 i 个元素在第 j 列的构造方案数
                long count;
                if(i - 1 == 0) {
                    count = occurTime[j - 1][target.charAt(i - 1) - 'a'];
                } else {
                    count = dp1[i - 1][j - 1] * occurTime[j - 1][target.charAt(i - 1) - 'a'];
                }
                dp1[i][j] = (dp1[i][j - 1] + count) % 1000000007;
            }
        }
        return (int)dp1[m][oneWordLen];
    }
}