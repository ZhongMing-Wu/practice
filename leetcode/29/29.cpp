class Solution {
public:
    int divide(int dividend, int divisor) {
        if(divisor == 1) 
            return dividend;
        if(divisor == 0)
            return 0;
        if(divisor == -1 && dividend == INT_MIN) 
            return INT_MAX;
        int  mark = (dividend > 0) ^ (divisor > 0);
        int result = 0;
        long a = abs((long)dividend);
        long b = abs((long)divisor);
        for(int i = 31; i >= 0; i--) {
            if((a >> i) >= b) {
                result += 1 << i;
                a -= b << i;
            }
        }
        return mark == 0?result : -result;
    }
};