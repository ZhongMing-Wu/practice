type MyIntSlice [][]int

func (m MyIntSlice) Len() int {
	return len(m)
}

func (m MyIntSlice) Less(i, j int) bool {
	return m[i][0] - m[i][1] < m[j][0] - m[j][1]
}

func (m MyIntSlice) Swap(i, j int) {
	m[i], m[j] = m[j], m[i]
}

func twoCitySchedCost(costs [][]int) int {
    var myCosts MyIntSlice = costs
	sort.Sort(myCosts)
	ans, length := 0, len(myCosts) / 2
	for i, j := 0, length; i < length; i, j = i + 1, j + 1 {
		ans += myCosts[i][0] + myCosts[j][1]
	}
	return ans
}