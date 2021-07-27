class Solution {
    public int minFlips(int a, int b, int c) {
        int changeTimes = 0;
        for(int i = 30; i >= 0; --i) {
            int indexA = (a >>> i) & 1;
            int indexB = (b >>> i) & 1;
            int indexC = (c >>> i) & 1;
            if((indexA | indexB) != indexC) {
                if(indexC == 1) {
                    ++changeTimes;
                } else {
                    changeTimes += indexA + indexB;
                }
            }
        }
        return changeTimes;
    }
}