class Solution {
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);

        int maxHorizonGap = 0, maxVerticalGap = 0;
        int preHorizonLoc = 0;
        for(int i = 0; i < horizontalCuts.length; i++) {
            maxHorizonGap = Math.max(maxHorizonGap, horizontalCuts[i] - preHorizonLoc);
            preHorizonLoc = horizontalCuts[i];
        }
        maxHorizonGap = Math.max(maxHorizonGap, h - preHorizonLoc);

        int preVerticalLoc = 0;
        for(int i = 0; i < verticalCuts.length; i++) {
            maxVerticalGap = Math.max(maxVerticalGap, verticalCuts[i] - preVerticalLoc);
            preVerticalLoc = verticalCuts[i];
        }
        maxVerticalGap = Math.max(maxVerticalGap, w - preVerticalLoc);

        long area = (long) maxHorizonGap * maxVerticalGap;
        area = area % 1000000007;
        return (int) area;
    }
}