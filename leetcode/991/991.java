class Solution {
    public int brokenCalc(int startValue, int target) {
        if(startValue >= target) {
            return startValue - target;
        }

        int operateCount = 0;
        int curValue = target;
        while(true) {
            if(startValue >= curValue / 2 + 1) {
                break;
            }
            if(curValue % 2 == 1) {
                curValue++;
                operateCount++;
            }
            curValue /= 2;
            operateCount++;
        }   

        if(startValue == curValue) {
            return operateCount;
        }

        if(curValue % 2 == 1) {
            curValue++;
            operateCount++;
        }
        return operateCount + Math.min(startValue * 2 - curValue + 1, startValue - curValue / 2 + 1);
    }
}