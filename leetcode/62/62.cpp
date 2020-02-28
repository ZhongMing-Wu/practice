class Solution {
public:
    int uniquePaths(int m, int n) {
        if(m == 1 || n == 1)
            return 1;
        int minVal = min(m, n) - 1;
        int maxVal = max(m, n);
        int totalWays = 0;
        for(int i = 1; i <= minVal; i++) {
            if(i == 1) {
                totalWays += calculateTotalWays(maxVal, i);
            }
            else {
                totalWays += calculateTotalWays(maxVal, i) * calculateTotalWays(minVal - 1, i - 1);
            }
        }
        return totalWays;
    }
    //该函数用于计算从down个元素中拿出up个元素，有多少种情况
    long long calculateTotalWays(int down, int up) { 
        if(up * 2 > down) {
            up = down - up;
        }
        long long numerator = 1, denominator = 1;
        while(up) {
            numerator *= down;
            denominator *= up;
            up--;
            down--;
        }
        return numerator / denominator;
    } 
};