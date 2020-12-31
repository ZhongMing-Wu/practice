class Solution {
    public int maxTurbulenceSize(int[] arr) {
        if(arr.length == 1) {
            return 1;
        }
        int[] compare = new int[arr.length - 1]; // -1: < 0: = 1: >
        for(int i = 0; i < arr.length - 1; i++) {
            if(arr[i] < arr[i + 1]) {
                compare[i] = -1;
            } else if(arr[i] == arr[i + 1]) {
                compare[i] = 0;
            } else {
                compare[i] = 1;
            }
        }
        int start = 0, end = 0, pre = 0, ans = 1;
        while(end < compare.length) {
            if(compare[end] != 0 && (compare[end] != compare[pre] || end == start)) {
                pre = end;
                end++;
                continue;
            }

            ans = Math.max(ans, end - start + 1);
            if(compare[end] == 0) {
                end++;
            }
            start = end;
            pre = end;
        }
        ans = Math.max(ans, end - start + 1);
        return ans;
    }
}