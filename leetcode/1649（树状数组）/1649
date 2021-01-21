class Solution {
    Set<Integer> sortInstructions;
    Map<Integer, Integer> toRank;
    int[] treeArr;
    public int createSortedArray(int[] instructions) {
        int n = instructions.length;
        sortInstructions = new TreeSet<>();
        toRank = new HashMap<>();
        for(int i = 0; i < n; ++i) {
            sortInstructions.add(instructions[i]);
        }

        int curRank = 1;
        for(int num : sortInstructions) {
            toRank.put(num, curRank++);
        }

        treeArr = new int[sortInstructions.size() + 1];
        int ans = 0;
        for(int i = 0; i < n; ++i) {
            curRank = toRank.get(instructions[i]);
            ans += calculateMinCost(i, curRank);
            ans %= 1000000007;
            updateTreeArr(curRank);
        }

        return ans;
    }

    private int calculateMinCost(int curTotal, int curRank) {
        int rank = curRank;
        int leftVal = 0, rightVal = 0, temp = 0;
        while(rank != 0) {
            temp += treeArr[rank];
            rank -= lowBit(rank);
        }
        rightVal = curTotal - temp;
        rank = curRank - 1;
        while(rank != 0) {
            leftVal += treeArr[rank];
            rank -= lowBit(rank);
        }

        return Math.min(leftVal, rightVal);
    }

    private void updateTreeArr(int curRank) {
        int rank = curRank;
        while(rank < treeArr.length) {
            ++treeArr[rank];
            rank += lowBit(rank);
        }
    } 

    private int lowBit(int num) {
        return num & (-num);
    }
}