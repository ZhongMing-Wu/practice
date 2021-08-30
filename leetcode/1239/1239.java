class Solution {
    public int maxLength(List<String> arr) {
        List<Integer> masks = new ArrayList<>();
        int maxLength = 0;
        for(String s : arr) {
            int mask = 0;
            for(int i = 0; i < s.length(); i++) {
                if((mask & 1 << s.charAt(i) - 'a') != 0) {
                    mask = 0;
                    break;
                }
                mask = mask | 1 << s.charAt(i) - 'a';
            }
            if(mask == 0) {
                continue;
            }
            maxLength = Math.max(maxLength, Integer.bitCount(mask));
            masks.add(mask);
            for(int i = 0; i < masks.size(); i++) {
                if((masks.get(i) & mask) == 0) {
                    masks.add(masks.get(i) | mask);
                    maxLength = Math.max(maxLength, Integer.bitCount(masks.get(i) | mask));
                }
            }
        }
        return maxLength;
    }
}