class Solution {
    public int multiply(int A, int B) {
        if(A < B) {
            int temp = A;
            A = B;
            B = temp;
        }
        return doMultiply(A, B);
    }

    private int doMultiply(int A, int B) {
        if(B == 0) {
            return 0;
        }
        if(B == 1) {
            return A;
        }
        if(B % 2 == 0) {
            return multiply(A << 1, B >> 1);
        }
        return multiply(A << 1, (B - 1) >> 1) + A;
    }
}