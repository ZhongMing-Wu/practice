class Solution {
    public int[] countSubgraphsForEachDiameter(int n, int[][] edges) {
        int[][] dis = new int[n + 1][n + 1];
        // 初始化
        for(int i = 1; i <= n; ++i) {
            for(int j = 1; j <= n; ++j) {
                if(i == j) {
                    dis[i][j] = 0;
                    continue;
                }
                dis[i][j] = n;
            }
        }
        int[] dp = new int[1 << n];
        // 根据边进行初始化
        for(int[] edge : edges) {
            dis[edge[0]][edge[1]] = 1;
            dis[edge[1]][edge[0]] = 1;

            dp[(1 << (edge[0] - 1)) + (1 << (edge[1] - 1))] = 1;
        }

        // floyed 算法
        for(int k = 1; k <= n; ++k) {
            for(int i = 1; i <= n; ++i) {
                for(int j = 1; j <= n; ++j) {
                    if(dis[i][k] != n && dis[j][k] != n) {
                        dis[i][j] = Math.min(dis[i][j], dis[i][k] + dis[j][k]);
                    }
                }
            }
        }

        for(int i = 0; i < dp.length; ++i) {
            if(dp[i] == 0) {
                continue;
            }
            // 分别添加第 j 个点
            for(int j = 0; j < n; ++j) {
                // 重复点或已计算子树集跳过
                if((i & 1 << j) != 0 || dp[i + (1 << j)] != 0) {
                    continue;
                }

                // 判断第 j + 1 个点是否可以和 i 子树构成一个新的子树
                for(int k = 0; k < n; ++k) {
                    if((i & (1 << k)) != 0 && dis[k + 1][j + 1] == 1) {
                        dp[i + (1 << j)] = dp[i];
                    }
                }

                // 计算最大距离
                if(dp[i + (1 << j)] != 0) {
                    for(int k = 0; k < n; ++k) {
                        if((i & (1 << k)) != 0) {
                            dp[i + (1 << j)] = Math.max(dp[i + (1 << j)], dis[k + 1][j + 1]);
                        }
                    }
                }
            }
        }
        int[] ans = new int[n - 1];
        for(int i = 0; i < dp.length; ++i) {
            if(dp[i] != 0) {
                ans[dp[i] - 1]++;
            }
        }
        return ans;
    }
}