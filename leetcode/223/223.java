class Solution {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int area1, area2;
        area1 = (C - A) * (D - B);
        area2 = (G - E) * (H - F);

        int coverHeight, coverLen, coverArea;

        if(Math.min(D, H) < Math.max(B, F)) {
            coverArea = 0;
        }
        else if(Math.min(C, G) < Math.max(A, E)) {
            coverArea = 0;
        }
        else {
            coverArea = (Math.min(D, H) - Math.max(B, F)) * (Math.min(C, G) - Math.max(A, E));
        }

        return area1 + area2 - coverArea;
    }
}