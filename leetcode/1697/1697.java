class Solution {
    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        Map<String, Integer> toLoc = new HashMap<>(queries.length);
        int[] toFather = new int[n];

        for(int i = 0; i < n; i++) {
            toFather[i] = i;
        }
        boolean[] ans = new boolean[queries.length];

        Arrays.sort(edgeList, (int[] a, int[] b) -> {
            return a[2] - b[2];
        });

        recordLoc(toLoc, queries);
        Arrays.sort(queries, (int[] a, int[] b) -> {
            return a[2] - b[2];
        });

        int j = -1;
        for(int i = 0; i < queries.length; i++) {
            while(j < edgeList.length - 1 && edgeList[j + 1][2] < queries[i][2]) {
                j++;
                int father1 = findFather(edgeList[j][0], toFather);
                int father2 = findFather(edgeList[j][1], toFather);
                unionTwoNodes(father1, father2, toFather);
            }
            if(findFather(toFather[queries[i][0]], toFather) == findFather(toFather[queries[i][1]], toFather)) {
                String temp = String.valueOf(queries[i][0]) + String.valueOf(queries[i][1]) + String.valueOf(queries[i][2]);
                ans[toLoc.get(temp)] = true;
            }
        }
        return ans;
    }

    private void recordLoc(Map<String, Integer> toLoc, int[][] arrays) {
        for(int i = 0; i < arrays.length; i++) {
            String temp = String.valueOf(arrays[i][0]) + String.valueOf(arrays[i][1]) + String.valueOf(arrays[i][2]);
            toLoc.put(temp, i);
        }
    }

    private int findFather(int num, int[] toFather) {
        int curVal = num;
        while(curVal != toFather[curVal]) {
            curVal = toFather[curVal];
        }
        return curVal;
    }

    private void unionTwoNodes(int num1, int num2, int[] toFather) {
        int father1 = toFather[num1];
        int father2 = toFather[num2];

        toFather[father2] = father1;

        int temp = num1;
        while(toFather[temp] != temp) {
            int originFather = toFather[temp];
            toFather[temp] = father1;
            temp = originFather;
        }
    }
}