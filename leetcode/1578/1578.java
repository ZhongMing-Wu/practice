class Solution {
    public int minCost(String s, int[] cost) {
        int index = 0, len = s.length(), costSum = 0;
        while(index < len) {
            int[] info = findCommonElementsRange(s, index, cost);
            index = info[2];
            costSum += (info[0] - info[1]);
        }
        return costSum;
    }

    public int[] findCommonElementsRange(String s, int index, int[] cost) {
        int sum = 0, maxCost = 0, len = s.length();
        char target = s.charAt(index);
        while(index < len && s.charAt(index) == target) {
            sum += cost[index];
            maxCost = Math.max(maxCost, cost[index]);
            index++;
        }
        return new int[] {sum, maxCost, index};
    }
}