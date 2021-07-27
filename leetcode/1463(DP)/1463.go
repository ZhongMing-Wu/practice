func cherryPickup(grid [][]int) int {
	colLen := len(grid[0])
	dp := make([][]int, colLen)
	nextDp := make([][]int, colLen)
	for i := 0; i < colLen; i++ {
		dp[i] = make([]int, colLen)
		nextDp[i] = make([]int, colLen)
		for j := 0; j < colLen; j++ {
			dp[i][j] = -1
			nextDp[i][j] = -1
		}
	}
	dp[0][colLen-1] = grid[0][0] + grid[0][colLen-1]

	rowLen := len(grid)
	for i := 1; i < rowLen; i++ {
		for col1 := 0; col1 < colLen; col1++ {
			for col2 := 0; col2 < colLen; col2++ {
				nextDp[col1][col2] = doHandle(i, col1, col2, colLen, dp, grid)
			}
		}
		temp := dp
		dp = nextDp
		nextDp = temp
	}

	ans := 0
	for _, v1 := range dp {
		for _, v2 := range v1 {
			ans = int(math.Max(float64(ans), float64(v2)))
		}
	}
	return ans
}

func doHandle(rowNum, col1, col2, colLen int, dp [][]int, grid [][]int) int {
	// 这里应该赋初始值为 -1，不能赋 0，因为如果该节点所有情况下均不成立，应该标记为 -1 表示从固定起点出发无法到达此处，如果标记为 0 表示可以到达，会出现问题。
	ans := -1
	for c1 := col1 - 1; c1 <= col1 + 1; c1++ {
		for c2 := col2 - 1; c2 <= col2 + 1; c2++ {
			if c1 >= 0 && c1 < colLen && c2 >= 0 && c2 < colLen && dp[c1][c2] != -1{
				var curRowVal int
				if col1 == col2 {
					curRowVal = dp[c1][c2] + grid[rowNum][col1]
				} else {
					curRowVal = dp[c1][c2] + grid[rowNum][col1] + grid[rowNum][col2]
				}
				ans = int(math.Max(float64(ans), float64(curRowVal)))
			}
		}
	}
	return ans
}