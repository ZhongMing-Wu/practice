class Solution {
    int[] addAToMinSteps = new int[10];
    public String findLexSmallestString(String s, int a, int b) {
        initMinSteps(a);
        int[] strArr = new int[s.length()];
        for(int i = 0; i < s.length(); ++i) {
            strArr[i] = s.charAt(i) - '0';
        }
        int gcd = getGCD(s.length(), b);
        String ans = null;
        for(int i = 0; i < s.length(); i += gcd) {
            String temp = tackleStr(strArr, a, b);
            if(ans == null) {
                ans = temp;
            } else {
                ans = ans.compareTo(temp) < 0 ? ans : temp;
            }
            rotateStr(strArr, gcd);
        }

        return ans;
    }

    private void initMinSteps(int a) {
        for(int i = 1; i < 10; ++i) {
            int minVal = i, curVal = (i + a) % 10;
            while(i != curVal) {
                minVal = Math.min(minVal, curVal);
                curVal = (curVal + a) % 10;
            }

            int steps = 0;
            while(curVal != minVal) {
                curVal = (curVal + a) % 10;
                ++steps;
            }
            addAToMinSteps[i] = steps;
        }
    }

    private int getGCD(int x, int y) {
        while(y != 0) {
            int temp = x % y;
            x = y;
            y = temp;
        }
        return x;
    }

    private void rotateStr(int[] strArr, int rotate) {
        for(int i = 0, j = rotate - 1; i < j; ++i, --j) {
            int temp = strArr[i];
            strArr[i] = strArr[j];
            strArr[j] = temp;
        }

        for(int i = rotate, j = strArr.length - 1; i < j; ++i, --j) {
            int temp = strArr[i];
            strArr[i] = strArr[j];
            strArr[j] = temp;
        }
        
        for(int i = 0, j = strArr.length - 1; i < j; ++i, --j) {
            int temp = strArr[i];
            strArr[i] = strArr[j];
            strArr[j] = temp;
        }
    } 

    private String tackleStr(int[] strArr, int a, int b) {
        int minSteps = addAToMinSteps[strArr[1]];
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < strArr.length; i += 2) {
            strArr[i] = (strArr[i] + a * minSteps) % 10;
        }

        for(int num : strArr) {
            sb.append(num);
        }
        String s = sb.toString();
        if(b % 2 == 1) {
            sb.delete(0, sb.length());
            minSteps = addAToMinSteps[strArr[0]];
            for(int i = 0; i < strArr.length; i += 2) {
                strArr[i] = (strArr[i] + a * minSteps) % 10;
            }
        }

        for(int num : strArr) {
            sb.append(num);
        }
        s = s.compareTo(sb.toString()) < 0 ? s : sb.toString();
        return s;
    }
}