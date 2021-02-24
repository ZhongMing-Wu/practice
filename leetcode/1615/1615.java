class Solution {
    public int maximalNetworkRank(int n, int[][] roads) {
        int[] neighbors = new int[n];
        Map<Integer, Integer> isCon = new HashMap<>();
        for(int i = 0; i < roads.length; ++i) {
            ++neighbors[roads[i][0]];
            ++neighbors[roads[i][1]];
            isCon.put(roads[i][0] * 100 + roads[i][1], 1);
            isCon.put(roads[i][1] * 100 + roads[i][0], 1);
        }

        int first = -1, second = -1;
        for(int i = 0; i < n; ++i) {
            if(neighbors[i] > first) {
                second =first;
                first = neighbors[i];
                continue;
            }
            if(neighbors[i] > second) {
                second = neighbors[i];
            }
        }

        List<Integer> firstVals = new ArrayList<>();
        List<Integer> secondVals = new ArrayList<>();
        for(int i = 0; i < n; ++i) {
            if(neighbors[i] == first) {
                firstVals.add(i);
            }
            if(neighbors[i] == second) {
                secondVals.add(i);
            }
        }

        int ans = -1;
        if(firstVals.size() > 1) {
            for(int i = 0; i < firstVals.size(); ++i) {
                for(int j = i + 1; j < firstVals.size(); ++j) {
                    if(isCon.getOrDefault(firstVals.get(i) * 100 + firstVals.get(j), 0) != 1) {
                        ans = first * 2;
                        break;
                    }
                }
                if(ans != -1) {
                    break;
                }
            }
            if(ans == -1) {
                ans = 2 * first - 1;
            }
        } else {
            int firstCity = firstVals.get(0);
            for(int i = 0; i < secondVals.size(); ++i) {
                if(isCon.getOrDefault(secondVals.get(i) * 100 + firstCity, 0) != 1) {
                    ans = first + second;
                    break;
                }
            }
            if(ans == -1) {
                ans = first + second - 1;
            }
        }
        return ans;
    }
}