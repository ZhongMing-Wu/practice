class Solution {
    public boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while(left < right) {
            while(left < s.length() && !Character.isDigit(s.charAt(left)) && !Character.isAlphabetic(s.charAt(left))) {
                left++;
            }
            while(right >= 0 && !Character.isDigit(s.charAt(right)) && !Character.isAlphabetic(s.charAt(right))) {
                right--;
            }
            if(left <= right && Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}