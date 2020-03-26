class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int checkNodeCount = 0, start = 0, end, leftGas = 0;
        int len = gas.length;
        while(checkNodeCount < len) {
            if(gas[start] - cost[start] >= 0) {
                leftGas = gas[start] - cost[start];
                end = (start + 1) % len;
                while(end != start && leftGas >= 0) {
                    leftGas += gas[end] - cost[end];
                    end = (end + 1) % len;
                }
                if(start == end) {
                    if(leftGas >= 0) {
                        return start;
                    }
                    else {
                        checkNodeCount = len;
                    }
                }
                else {
                    if(end > start) {
                        checkNodeCount += end - start;
                    }
                    else {
                        checkNodeCount += end + len - start;
                    }
                    start = end;
                }
                leftGas = 0;
            }
            else {
                start = (start + 1) % len;
                checkNodeCount++;
            }
        }
        return -1;
    }
}