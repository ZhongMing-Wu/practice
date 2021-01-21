class Solution {
    public int minimumEffort(int[][] tasks) {
        Arrays.sort(tasks, (int[] a, int[] b) -> {
            if(b[1] - b[0] > a[1] - a[0]) {
                return 1;
            }
            return -1;
        });    
        int sumNeed = 0, sumLeft = 0;
        for(int i = 0; i < tasks.length; i++) {
            if(sumLeft < tasks[i][1]) {
                sumNeed += tasks[i][1] - sumLeft;
                sumLeft = tasks[i][1];
            }
            sumLeft -= tasks[i][0];
        }
        return sumNeed;
    }
}