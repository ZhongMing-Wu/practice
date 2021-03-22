class Solution {
    public List<Integer> partitionLabels(String S) {
        int[] endIndex = new int[26];
        Arrays.fill(endIndex, -1);
        for(int i = S.length() - 1; i >= 0; --i) {
            int index = S.charAt(i) - 'a';
            if(endIndex[index] == -1) {
                endIndex[index] = i;
            }
        }
        List<Integer> ans = new ArrayList<>();
        int start = 0, end = -1;
        for(int i = 0; i < S.length(); ++i) {
            end = Math.max(end, endIndex[S.charAt(i) - 'a']);
            if(i == end) {
                ans.add(end - start + 1);
                start = end + 1;
            }
        }
        return ans;
    }
}