class Solution {
    public int videoStitching(int[][] clips, int T) {
        int[] maxn = new int[T];
        for(int i = 0; i < clips.length; ++i) {
            if(clips[i][0] < T) {
                maxn[clips[i][0]] = Math.max(maxn[clips[i][0]], clips[i][1]);
            }
        }

        int preEdge = 0, curEdge = 0, minSeqCount = 0;
        for(int i = 0; i < T; ++i) {
            curEdge = Math.max(maxn[i], curEdge);
            if(i == curEdge) {
                return -1;
            }
            if(i == preEdge) {
                ++minSeqCount;
                preEdge = curEdge;
                if(preEdge >= T) {
                    break;
                }
            }
        }
        return minSeqCount;
    }
}