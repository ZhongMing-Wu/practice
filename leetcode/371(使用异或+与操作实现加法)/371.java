class Solution {
    public int getSum(int a, int b) {
        while(b != 0) {
             int leftCarry = (a & b) << 1;
             a = a ^ b;
             b = leftCarry;
        }
        return a;
    }
}