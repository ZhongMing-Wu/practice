class Solution {
    public int smallestFactorization(int a) {
        if(a < 10) {
            return a;
        }
        List<Integer> facVals = new ArrayList<>();
        int num = a, facVal = 9;
        while(num > 1) {
            if(num % facVal == 0) {
                num /= facVal;
                facVals.add(facVal);
            } else {
                --facVal;
                if(facVal < 2) {
                    break;
                }
            }
        }
        if(num > 9) {
            return 0;
        }
        if(facVals.size() > 10) {
            return 0;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = facVals.size() - 1; i >= 0; --i) {
            sb.append(facVals.get(i));
        }

        long ans = Long.valueOf(sb.toString());
        if(ans > Integer.MAX_VALUE) {
            return 0;
        }
        return (int) ans;
    }

}