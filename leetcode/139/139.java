class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        int[] dp = new int[s.length() + 1];  //dp存储长度为i的子串是否可以拆分为字典中的单词
        Set<String> wordSet = new HashSet<>(wordDict);
        dp[0] = 1;
        for(int i = 1; i <= s.length(); i++) {
            for(int start = i - 1; start >= 0; start--) {
                if(dp[start] == 1 && judgeOneWord(s.substring(start, i), wordSet)) {
                    dp[i] = 1;
                    break;
                }
            }
        }
        return dp[s.length()] == 1 ? true : false;
    }

    public boolean judgeOneWord(String partS, Set<String> wordSet) {
        if(wordSet.contains(partS)) {
            return true;
        }
        else {
            return false;
        }
    }
}