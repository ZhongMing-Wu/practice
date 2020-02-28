class Solution {
    public int countSubstrings(String s) {
        char[] sToChar = new char[2 * s.length() + 3];
        sToChar[0] = '@';
        sToChar[1] = '#';
        sToChar[sToChar.length - 1] = '$';
        int i = 2;
        for(char c : s.toCharArray()) {
            sToChar[i++] = c;
            sToChar[i++] = '#';
        }
        int[] Count = new int[sToChar.length];
        int center = 0, right = 0;
        for(i = 1; i < sToChar.length - 1; i++) {
            if(i < right) {
                Count[i] = Math.min(Count[center - (i - center)], right - i + 1);
            }
            while(sToChar[i + Count[i]] == sToChar[i - Count[i]]) {
                Count[i]++;
            }
            if(i + Count[i] - 1 > right) {
                center = i;
                right = i + Count[i] - 1;
            }
        }
        int ans = 0;
        for(i = 2; i < Count.length - 1; i++) {
            if(sToChar[i] == '#') {
                ans += (Count[i] - 1) / 2;
            }
            else {
                ans += Count[i] / 2;
            }
        }
        return ans;
    }
}