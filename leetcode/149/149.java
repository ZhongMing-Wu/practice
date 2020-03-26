class Solution {
    public int maxPoints(int[][] points) {
        int len = points.length;
        if(len < 3) {
            return len;
        }
        Map<String, Integer> duplication = new HashMap<>();
        Map<String, Integer> Count = new HashMap<>();
        String str = null;
        for(int i = 0; i < len; i++) {
            str = points[i][0] + "." + points[i][1];
            if(duplication.containsKey(str)) {
                duplication.put(str, duplication.get(str) + 1);
            }
            else {
                duplication.put(str, 0);
            }
        }
        int numerator, denominator, maxCount = 0, dup;
        for(int i = 0; i < len - 1; i++) {
            Count.clear();
            dup = duplication.get(points[i][0] + "." + points[i][1]);
            for(int j = i + 1; j < len; j++) {
                if(points[i][0] == points[j][0] && points[i][1] == points[j][1]) {
                    maxCount = Math.max(maxCount, dup + 1);
                }
                else if(points[i][0] == points[j][0]) {
                    str = points[i][0] + "x";
                    if(Count.containsKey(str)) {
                        Count.put(str, Count.get(str) + 1);
                    }
                    else {
                        Count.put(str, 2);
                    }
                    maxCount = Math.max(maxCount, Count.get(str) + dup);
                }
                else if(points[i][1] == points[j][1]) {
                    str = points[i][1] + "y";
                    if(Count.containsKey(str)) {
                        Count.put(str, Count.get(str) + 1);
                    }
                    else {
                        Count.put(str, 2);
                    }
                    maxCount = Math.max(maxCount, Count.get(str) + dup);
                }
                else {
                    numerator = points[i][0] - points[j][0];
                    denominator = points[i][1] - points[j][1];
                    int theGCD = gcd(numerator, denominator);
                    numerator /= theGCD;
                    denominator /= theGCD;
                    str = numerator + "." + denominator;
                    if(Count.containsKey(str)) {
                        Count.put(str, Count.get(str) + 1);
                    }
                    else {
                        Count.put(str, 2);
                    }
                    maxCount = Math.max(maxCount, Count.get(str) + dup);
                }
            }
        }
        return maxCount;
    }
    int gcd(int x, int y) {
        int temp;
        int tempX = x > 0 ? x : -x;
        int tempY = y > 0 ? y : -y;
        while(y != 0) {
            temp = x % y;
            x = y;
            y = temp;
        }
        return x;
    }
}