class Solution {
    public boolean canMeasureWater(int x, int y, int z) {
       if(z == 0) {
           return true;
       } 
       if(x + y < z) {
           return false;
       }
       if(x == 0 || y == 0) {
           return Math.max(x, y) == z ? true : false;
       }
       return z % getGreatestCommonDivisor(x, y) == 0 ? true : false;
    }

    private int getGreatestCommonDivisor(int x, int y) {
        int temp;
        while(x % y != 0) {
            temp = x % y;
            x = y;
            y = temp;
        }
        return y;
    }
}