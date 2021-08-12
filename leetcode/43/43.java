class Solution {
    public String multiply(String num1, String num2) {
        String ans = "0";
        int len1 = num1.length(), len2 = num2.length();
        if(len1 == 0 || len2 == 0) {
            return "0";
        }

        if(num1.charAt(0) == '0' || num2.charAt(0) == '0') {
            return "0";
        }

        for(int i = len1 - 1; i >= 0; i--) {
            StringBuilder partSum = multiplyOneNumber(num2, num1.charAt(i) - '0');
            if(partSum.length() != 0) {
                for(int j = 0; j < len1 - i - 1; j++) {
                    partSum.append(0);
                }
                ans = sum(partSum.toString(), ans);
            }
        }
        return ans;
    }

    private StringBuilder multiplyOneNumber(String num, int oneNumber) {
        if(oneNumber == 0) {
            return new StringBuilder();
        }
        StringBuilder sb = new StringBuilder();
        int flag = 0;
        for(int i = num.length() - 1; i >= 0; i--) {
            int val = (num.charAt(i) - '0') * oneNumber + flag;
            flag = 0;
            if(val >= 10) {
                flag = val / 10;
                val = val % 10;
            }
            sb.append(val);
        }
        if(flag > 0) {
            sb.append(flag);
        }

        return sb.reverse();
    }

    private String sum(String num1, String num2) {
        int len1 = num1.length(), len2 = num2.length();
        if(len1 == 0) {
            return num2;
        }

        if(len2 == 0) {
            return num1;
        }

        StringBuilder sb = new StringBuilder();
        int flag = 0, i = len1 - 1, j = len2 - 1;
        for(; i >= 0 && j >= 0; i--, j--) {
            int val = (num1.charAt(i) - '0') + (num2.charAt(j) - '0') + flag;
            flag = 0;
            if(val >= 10) {
                flag = 1;
                val %= 10;
            }
            sb.append(val);
        }
        while(i >= 0) {
            int val = num1.charAt(i--) - '0' + flag;
            flag = 0;
            if(val >= 10) {
                flag = 1;
                val %= 10;
            }
            sb.append(val);
        }

        while(j >= 0) {
            int val = num1.charAt(j--) - '0' + flag;
            flag = 0;
            if(val >= 10) {
                flag = 1;
                val %= 10;
            }
            sb.append(val);
        }
        if(flag > 0) {
            sb.append(1);
        }

        return sb.reverse().toString();
    }
}

