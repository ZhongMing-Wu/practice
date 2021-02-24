class Solution {
    public int countSubstrings(String s, String t) {
        int m = s.length(), n = t.length();
        // f[i][j] 表示分别从 s 的第 i 个位置和 t 的第 j 个位置
        // 向左相同的字符串个数
        int[][] f = new int[m + 1][n + 1];
        // g[i][j] 表示 s 前 i 个字符串和 t 的前 j 个字符串只差
        // 一个字符的子串数目
        int[][] g = new int[m + 1][n + 1];

        for(int i = 0; i <= m; ++i) {
            for(int j = 0; j <= n; ++j) {
                if(i == 0 || j == 0) {
                    continue;
                }
                if(s.charAt(i - 1) == t.charAt(j - 1)) {
                    f[i][j] = f[i - 1][j - 1] + 1;
                } else {
                    f[i][j] = 0;
                }
            }
        }
        int ans = 0;
        for(int i = 0; i <= m; ++i) {
            for(int j = 0; j <= n; ++j) {
                if(i == 0 || j == 0) {
                    continue;
                }
                if(s.charAt(i - 1) == t.charAt(j - 1)) {
                    g[i][j] = g[i - 1][j - 1];
                    ans += g[i][j];
                } else {
                    g[i][j] = f[i - 1][j - 1] + 1;
                    ans += g[i][j];
                }
            }
        }

        return ans;
    }
}