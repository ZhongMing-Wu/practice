class Solution {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        int len1 = rec1[2] - rec1[0], width1 = rec1[3] - rec1[1];
        int len2 = rec2[2] - rec2[0], width2 = rec2[3] - rec2[1];

        int sumLen = Math.max(Math.abs(rec2[2] - rec1[0]), Math.abs(rec1[2] - rec2[0]));
        int sumWidth = Math.max(Math.abs(rec2[3] - rec1[1]), Math.abs(rec1[3] - rec2[1]));

        if(sumLen < (long)len1 + len2 && sumWidth < (long)width1 + width2) {
            return true;
        }
        return false;
    }
}