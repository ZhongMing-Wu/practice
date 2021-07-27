func isSub(sub, par []string) bool {
	lenSub, lenPar := len(sub), len(par)
	pSub, pPar := 0, 0
	for pSub < lenSub && pPar < lenPar {
		if sub[pSub] == par[pPar] {
			pSub++
			pPar++
		} else {
			pPar++
		}
 	}
 	return pSub == lenSub
}
func peopleIndexes(favoriteCompanies [][]string) []int {
	for _, com := range favoriteCompanies {
		sort.Strings(com)
	}
	
	sorted := make([][]string, len(favoriteCompanies))
	copy(sorted, favoriteCompanies)
	sort.Slice(sorted, func(i, j int) bool {
		return len(sorted[i]) >= len(sorted[j])
	})
	
	var ans = make([]int, 0, len(favoriteCompanies))
	
	for i, sub := range favoriteCompanies {
		flag := false
		for _, par := range sorted {
			if len(sub) >= len(par) {
				break
			}
			if isSub(sub, par) {
				flag = true
				break
			}
		}
		if !flag {
			ans = append(ans, i)
		}
	}
	return ans
}