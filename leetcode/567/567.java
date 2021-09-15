class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if(s1.length() > s2.length()) {
            return false;
        }

        if(s1.length() == 0) {
            return true;
        }

        int count = s1.length();
        HashMap<Character, Integer> toOccurTime = new HashMap<>();
        for(int i = 0; i < count; i++) {
            char c = s1.charAt(i);
            toOccurTime.put(c, toOccurTime.getOrDefault(c, 0) + 1);
        }

        int left = 0, right = 0;
        while(right < s2.length()) {
            if(left > right) {
                right = left;
                continue;
            }
            char c2 = s2.charAt(right);
            if(!toOccurTime.containsKey(c2) || toOccurTime.get(c2) == 0) {
                char c1 = s2.charAt(left);
                left++;
                if(toOccurTime.containsKey(c1)) {
                    count++;
                    toOccurTime.put(c1, toOccurTime.get(c1) + 1);
                }
                continue;
            }
            toOccurTime.put(c2, toOccurTime.get(c2) - 1);
            count--;
            right++;
            if(count == 0) {
                return true;
            }
        }
        return false;
    }
}