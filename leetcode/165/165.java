class Solution {
    public int compareVersion(String version1, String version2) {
        int num1, num2, index1 = 0, index2 = 0, len1 = version1.length(), len2 = version2.length(), start, toInt1, toInt2;
        String str1, str2;
        while(index1 < len1 && index2 < len2) {
            start = index1;
            while(index1 < len1 && version1.charAt(index1) != '.') {
                index1++;
            }
            str1 = version1.substring(start, index1++);
            start = index2;
            while(index2 < len2 && version2.charAt(index2) != '.') {
                index2++;
            }
            str2 = version2.substring(start, index2++);
            toInt1 = Integer.valueOf(str1);
            toInt2 = Integer.valueOf(str2);
            if(toInt1 > toInt2) {
                return 1;
            }
            else if(toInt1 < toInt2) {
                return -1;
            }
        }
        while(index1 < len1) {
            toInt2 = 0;
            start = index1;
            while(index1 < len1 && version1.charAt(index1) != '.') {
                index1++;
            }
            str1 = version1.substring(start, index1++);
            toInt1 = Integer.valueOf(str1);
            if(toInt1 > toInt2) {
                return 1;
            }
            else if(toInt1 < toInt2) {
                return 0;
            }
        }
        while(index2 < len2) {
            toInt1 = 0;
            start = index2;
            while(index2 < len2 && version2.charAt(index2) != '.') {
                index2++;
            }
            str2 = version2.substring(start, index2++);
            toInt2 = Integer.valueOf(str2);
            if(toInt1 > toInt2) {
                return 1;
            }
            else if(toInt1 < toInt2) {
                return -1;
            }
        }
        return 0;
    }
}