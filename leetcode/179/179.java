class Solution {
    public String largestNumber(int[] nums) {
        String[] strNums = new String[nums.length];
        for(int i = 0; i < nums.length; i++) {
            strNums[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(strNums, (s1, s2) -> {
            String tempStr1 = s1 + s2;
            String tempStr2 = s2 + s1;
            for(int i = 0; i < tempStr1.length(); i++) {
                if(tempStr1.charAt(i) > tempStr2.charAt(i)) {
                    return -1;
                }
                else if(tempStr1.charAt(i) < tempStr2.charAt(i)) {
                    return 1;
                }
            }
            return 1;
        });
        StringBuilder ans = new StringBuilder();
        for(String str : strNums) {
            ans.append(str);
        }
        if(ans.charAt(0) == '0') {
            return "0";
        }
        return ans.toString();
    }
}