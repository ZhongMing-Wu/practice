class Solution {
    public int videoStitching(int[][] clips, int T) {
        if(T == 0) {
            return 0;
        }
        Arrays.sort(clips, (a, b) -> {
            if(a[0] != b[0]) {
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });
        int minSeqCount = 0, preEdge = 0, curMaxEdge = 0;
        for(int i = 0; i < clips.length;) {
            if(clips[i][0] > preEdge) {
                preEdge = curMaxEdge;
                ++minSeqCount;
                if(preEdge >= T) {
                    break;
                }
                if(preEdge < clips[i][0]) {
                    return -1;
                }
            }
            curMaxEdge = Math.max(curMaxEdge, clips[i][1]);
            ++i;
        }
        if(preEdge >= T) {
            return minSeqCount;
        }
        if(curMaxEdge >= T) {
            return minSeqCount + 1;
        }
        return -1;
    }
}