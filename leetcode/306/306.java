class Solution {
    public boolean isAdditiveNumber(String num) {
        if(0 == num.length()) {
            return true;
        }

        for(int i = 1; i < num.length(); i++) {
            for(int j = 1; j < num.length() - i; j++) {
                if(validation(0, i, j, num)) {
                    return true;
                }
            }
        }
        return false;
    }

    public String add(String s1, String s2) {
        int flag = 0;
        if(s1.length() > s2.length()) {
            String temp = s1;
            s1 = s2;
            s2 = temp;
        }

        StringBuilder sb = new StringBuilder();

        int i, j, a, b, sum;
        for(i = s1.length() - 1, j = s2.length() - 1; i >= 0; i--, j--) {
            a = s1.charAt(i) - '0';
            b = s2.charAt(j) - '0';
            sum = a + b + flag;
            if(sum >= 10) {
                sb.append(sum % 10);
                flag = 1;
            } else {
                sb.append(sum);
                flag = 0;
            }
        }

        for(; j >= 0; j--) {
            b = s2.charAt(j) - '0';
            sum = b + flag;
            if(sum >= 10) {
                sb.append(sum % 10);
                flag = 1;
            } else {
                sb.append(sum);
                flag = 0;
            }
        }

        if(1 == flag) {
            sb.append(1);
        }

        return sb.reverse().toString();
    }

    boolean validation(int start, int len1, int len2, String num) {
        if(start + len1 + len2 == num.length()) {
            return true;
        }
        String s1 = num.substring(start, start + len1);
        if('0' == s1.charAt(0) && s1.length() > 1) {
            return false;
        }

        String s2 = num.substring(start + len1, start + len1 + len2);
        if('0' == s2.charAt(0) && s2.length() > 1) {
            return false;
        }

        String sum = add(s1, s2);
        if(sum.length() + start + len1 + len2 > num.length()) {
            return false;
        }

        String expectNum = num.substring(start + len1 + len2, sum.length() + start + len1 + len2);

        if(sum.equals(expectNum)) {
            return validation(start + len1, len2, sum.length(), num);
        }

        return false;
    }
}