    class Solution {
    List<List<String>> ans;
    public List<List<String>> partition(String s) {
        int len = s.length();
        ans = new ArrayList<List<String>>();
        if(len == 0) {
            return ans;
        }
        boolean[][] dp = new boolean[len][len];
        for(int i = 0; i < len; i++) {
            for(int j = 0; j <= i; j++) {
                if(s.charAt(j) == s.charAt(i) && (i - j < 2 || dp[j + 1][i - 1])) {
                    dp[j][i] = true;
                }
            }
        }
        List<String> oneAns = new ArrayList<>();
        startFind(s, oneAns, dp, 0);
        return ans;
    }
    
    void startFind(String s, List<String> oneAns, boolean[][] dp, int start) {
        int len = s.length();
        if(start == len) {
            List<String> temp = new ArrayList<>(oneAns);
            ans.add(temp);
            return;
        }
        for(int i = start; i < len; i++) {
            if(dp[start][i]) {
                oneAns.add(s.substring(start, i + 1));
                startFind(s, oneAns, dp, i + 1);
                oneAns.remove(oneAns.size() - 1);
            }
        }
    }
}