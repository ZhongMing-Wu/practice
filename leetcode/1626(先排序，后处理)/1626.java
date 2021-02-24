class Solution {
    public int bestTeamScore(int[] scores, int[] ages) {
      int n = scores.length;
      int[][] player = new int[n][2];
      for(int i = 0 ; i < n; ++i) {
          player[i][0] = ages[i];
          player[i][1] = scores[i];
      }  

      Arrays.sort(player, (a, b) -> {
          if(a[0] != b[0]) {
              return a[0] - b[0];
          }
          return a[1] - b[1];
      });

      int[] dp = new int[n];
      dp[0] = player[0][1];
      int ans = dp[0];
      for(int i = 1; i < n; ++i) {
          dp[i] = player[i][1];
          for(int j = 0; j < i; ++j) {
              if(player[j][1] <= player[i][1]) {
                  dp[i] = Math.max(dp[i], dp[j] + player[i][1]);
              }
          }
          ans = Math.max(ans, dp[i]);
      }
      return ans;
    }
}