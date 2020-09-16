class Solution {
    public int hIndex(int[] citations) {
        if(citations.length == 0) {
            return 0;
        }
        int left = 0, right = citations.length - 1;
        int middle, len = right;
        while(left < right) {
            middle = (left + right) / 2;
            if(len - middle + 1 > citations[middle]) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }
        return Math.min(len -right + 1, citations[left]);
    }
}