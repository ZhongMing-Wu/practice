class Solution {
    public int numPairsDivisibleBy60(int[] time) {
        int[] mod = new int[60];
        for(int val : time) {
            mod[val % 60]++;
        }

        int count = 0;
        if(mod[0] != 0) {
            count += mod[0] * (mod[0] - 1) / 2;
        }
        if(mod[30] != 0) {
            count += mod[30] * (mod[30] - 1) / 2;
        }

        for(int i = 1, j = 59; i < j; i++, j--) {
            count += mod[i] * mod[j];
        }
        return count;
    }
}