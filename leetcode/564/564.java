class Solution {
    public String nearestPalindromic(String n) {
        int len = n.length();
        if(len == 1) {
            return String.valueOf(Long.valueOf(n) - 1);
        }
        StringBuilder sb1 = new StringBuilder(n.substring(0, len / 2));
        StringBuilder sb2 = new StringBuilder();
        sb2.append(sb1);
        if(len % 2 == 1) {
            sb2.append(n.charAt(len / 2));
        }
        sb2.append(sb1.reverse());
        String s1 = sb2.toString();
        if(s1.equals(n)) {
            s1 = "0";
        }

        String s2 = getClosestLessThanStr(new StringBuilder(sb2));
        String s3 = getClosestMoreThanStr(new StringBuilder(sb2));
        long num1 = Long.valueOf(s1);
        long num2 = Long.valueOf(s2);
        long num3 = Long.valueOf(s3);
        long src = Long.valueOf(n);
        long diff1 = Math.abs(num1 - src);
        long diff2 = Math.abs(num2 - src);
        long diff3 = Math.abs(num3 - src);
        if(diff2 <= diff1 && diff2 <= diff3) {
            return s2;
        }
        if(diff1 < diff2 && diff1 <= diff3) {
            return s1;
        }
        return s3;
    }

    public String getClosestLessThanStr(StringBuilder str) {
        int len = str.length();
        int left, right;
        if(len % 2 == 1) {
            left = right = len / 2;
        } else {
            left = len / 2 - 1;
            right = len / 2;
        }

        while(left >= 0) {
            char c = str.charAt(left);
            if(c != '0') {
                str.setCharAt(left, (char)(c - 1));
                str.setCharAt(right, (char)(c - 1));
                break;
            }
            str.setCharAt(left, '9');
            str.setCharAt(right, '9');
            left--;
            right++;
        }
        if(left == 0 && str.charAt(left) == '0') {
            str.setCharAt(0, '9');
            str.deleteCharAt(len - 1);
        }
        return str.toString();
    }

    public String getClosestMoreThanStr(StringBuilder str) {
        int len = str.length();
        int left, right;
        if(len % 2 == 1) {
            left = right = len / 2;
        } else {
            left = len / 2 - 1;
            right = len / 2;
        }

        while(left >= 0) {
            char c = str.charAt(left);
            if(c != '9') {
                str.setCharAt(left, (char)(c + 1));
                str.setCharAt(right, (char)(c + 1));
                break;
            }
            str.setCharAt(left, '0');
            str.setCharAt(right, '0');
            left--;
            right++;
        }
        if(left < 0) {
            str.setCharAt(0, '1');
            str.append(1);
        }
        return str.toString();
    }
}