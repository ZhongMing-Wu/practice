class Solution {
    public int hIndex(int[] citations) {
        int len = citations.length;
        if(len == 0) {
            return 0;
        }
        int[] count = new int[len + 1];
        for(int i = 0; i < len; i++) {
            int temp = Math.min(len, citations[i]);
            count[temp]++;
        }

        int h = len;
        int curCite = findNext(count, 0);
        while(h > curCite) {
            if(count[curCite] != 0) {
                h--;
                count[curCite]--;
            } else {
                curCite = findNext(count, curCite);
            }
        }
        return h;
    }

    private int findNext(int[] count, int index) {
        while(count[index] == 0 && index < count.length) {
            index++;
        }
        return index;
    }
}