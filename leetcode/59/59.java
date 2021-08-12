class Solution {
    public int[][] generateMatrix(int n) {
        int[][] ans = new int[n][n];
        int count = 0, curValue = 1;
        int topLimit = 0, downLimit = n - 1, leftLimit = 0, rightLimit = n - 1;
        int curX = 0, curY = 0;
        while(count + 1 < n * n) {
            while(curY < rightLimit) {
                ans[curX][curY++] = curValue++;
                count++;
            }
            while(curX < downLimit) {
                ans[curX++][curY] = curValue++;
                count++;
            }
            while(curY > leftLimit) {
                ans[curX][curY--] = curValue++;
                count++;
            }
            while(curX > topLimit) {
                ans[curX--][curY] = curValue++;
                count++;
            }
            topLimit++;
            downLimit--;
            leftLimit++;
            rightLimit--;
            curY++;
            curX++;
        }   
        if(count < n * n) {
            ans[curX][curY] = curValue;
        }
        return ans;
    }
}