func equalSubstring(s string, t string, maxCost int) int {
    left, right, curCost, n := 0, 0, 0, len(s)
	maxTransferLen := 0
	for left < n {
		if curCost <= maxCost {
            if right == n {
                maxTransferLen = int(math.Max(float64(maxTransferLen), float64(right - left)))   
                break
            }
			curCost += doCalculateGap(s[right], t[right])
			right++
			continue
		}
		maxTransferLen = int(math.Max(float64(maxTransferLen), float64(right - left - 1)))
		curCost -= doCalculateGap(s[left], t[left])
		left++
		if left > right {
			right = left
		}
	}
	return maxTransferLen
}

func doCalculateGap(c1, c2 uint8) int {
	if c1 > c2 {
		return int(c1 - c2)
	} else {
		return int(c2 - c1)
	}
}