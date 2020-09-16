class Solution {
    public int[] singleNumber(int[] nums) {
        int a  = 0;  // a 最终的值是两个只出现一次的数的异或
        for(int num : nums) {
            a = a ^ num;
        }

        int loc = a & (~a + 1);  // loc 为 a 二进制形式第一个 1 出现的位置

        int val1 = 0, val2 = 0;
        for(int num : nums) {
            if((num & loc) == 0) {
                val1 = val1 ^ num;
            } else {
                val2 = val2 ^ num;
            }
        }

        int[] ans = new int[2];
        ans[0] = val1;
        ans[1] = val2;
        return ans;
    }
}