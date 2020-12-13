class Solution {
    public int bulbSwitch(int n) {
        return customizeSqrt(n);
    }

    public int customizeSqrt(int num) {
        double a = num, precision = 0.000001;
        while(a * a - num > precision) {
            a = (num + a * a) / (2 * a);
        }
        return (int)a;
    }
}