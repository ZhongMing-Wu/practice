class Solution {
    public boolean checkPalindromeFormation(String a, String b) {
            int left = a.length() / 2 - 1;
            left = Math.min(findLeftIndex(a, a, left), findLeftIndex(b, b, left));
            left = Math.min(findLeftIndex(a, b, left), findLeftIndex(b, a, left));

            return left == -1 ? true : false;
    }

    private  int findLeftIndex(String s_l, String s_r, int left) {
        int right = s_l.length() - left - 1;
        while(left >= 0) {
            if(s_l.charAt(left) != s_r.charAt(right)) {
                break;
            }
            --left;
            ++right;
        }
        return left;
    }
}