class Solution {
public:
    int mySqrt(int x) {
        if(x == 0) 
            return 0;
        long long a = x;
        while(a * a > x) {
            a = (a + x / a) / 2;
        }
        return (int)a;
    }
};