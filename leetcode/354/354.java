class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (int[] a, int[] b) -> {
            if(a[0] != b[0]) {
                return a[0] - b[0];
            }
            return b[1] - a[1];
        });

        List<Integer> dp = new ArrayList<>(envelopes.length);
        for(int i = 0; i < envelopes.length; i++) {
            binarySearch(dp, envelopes[i][1]);
        }
        return dp.size();
    }

    private void binarySearch(List<Integer> dp, int val) {
        if(dp.size() == 0 || dp.get(dp.size() - 1) < val) {
            dp.add(val);
            return;
        }
        int left =0, right = dp.size() - 1;
        int middle;
        while(left <= right) {
            middle = (left + right) / 2;
            if(dp.get(middle) == val) {
                return;
            }
            if(dp.get(middle) > val) {
                right = middle -1;
            } else {
                left = middle + 1;
            }
        }
        dp.set(left, val);
    }
}