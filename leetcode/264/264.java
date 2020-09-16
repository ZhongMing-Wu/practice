class Solution {
    public int nthUglyNumber(int n) {
        if(n <= 6) {
            return n;
        }
        int i, j ,k;  // i 对应 2, j 对应 3, k 对应 5

        int[] uglyVal = new int[1700];
        uglyVal[0] = 1;
        i = j = k = 0;
        
        int curNum = 1, nextVal;
        while(curNum < n) {
            nextVal = Math.min(Math.min(uglyVal[i] * 2, uglyVal[j] * 3), uglyVal[k] * 5);
            if(nextVal == uglyVal[k] * 5) {
                k++;
            } else if(nextVal == uglyVal[j] * 3) {
                j++;
            } else {
                i++;
            }

            if(uglyVal[curNum - 1] != nextVal) {
                uglyVal[curNum] = nextVal;
                curNum++;
            }
        }
        return uglyVal[curNum - 1];
    }
}