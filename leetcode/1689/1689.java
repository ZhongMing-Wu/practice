class Solution {
    public int minPartitions(String n) {
        int maxNum = -1;
        for(int i = 0; i < n.length(); i++) {
            maxNum = Math.max(maxNum, n.charAt(i) - '0');
        }
        return maxNum;
    }
}