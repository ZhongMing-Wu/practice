func numPermsDISequence(S string) int {
	n := len(S)
	var dp = make([][]int, n + 1)
	for i := 0; i <= n; i++ {
		dp[i] = make([]int, n + 1)
	}
	for i := 0; i <= n; i++ {
		dp[0][i] = 1
	}

    var MOD = 1000000007
	for i := 1; i <= n; i++ {
		if S[i - 1] == 'D' {
			for j := i - 1; j >= 0; j-- {
				dp[i][j] = dp[i][j + 1] + dp[i - 1][j]
				dp[i][j] %= MOD
			}
		} 
		if S[i - 1] == 'I' {
			for j := 1; j <= i; j++ {
				dp[i][j] = dp[i][j - 1] + dp[i - 1][j - 1]
				dp[i][j] %= MOD
			}
		}
	}
	
	var ans = 0
	for i := 0; i <= n; i++ {
		ans += dp[n][i]
		ans %= MOD
	}
	return ans
}