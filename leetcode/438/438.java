class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer>ans = new ArrayList<>();
        if(s.length() == 0 || s.length() < p.length()) {
            return ans;
        }
        //flag 表示当前缺少的元素个数
        int left = 0, right = 0, flag = p.length();  
        Map<Character, Integer> Count = new HashMap<>();
        for(int i = 0; i < p.length(); i++) {
            if(Count.containsKey(p.charAt(i)) == true) {
                Count.put(p.charAt(i), Count.get(p.charAt(i)) + 1);
            }
            else {
                Count.put(p.charAt(i), 1);
            }
        }
        for(; right < p.length(); right++) {
            if(Count.containsKey(s.charAt(right)) == true) {
                Count.put(s.charAt(right), Count.get(s.charAt(right)) - 1);
                if(Count.get(s.charAt(right)) >= 0) {
                    flag--;
                }
            }
        }
        right--;
        if(flag == 0) {
            ans.add(left);
        }
        while(right < s.length()) {
            if(Count.containsKey(s.charAt(left)) == true) {
                Count.put(s.charAt(left), Count.get(s.charAt(left)) + 1);
                if(Count.get(s.charAt(left)) > 0) {
                    flag++;
                }
            }
            left++;
            right++;
            if(right < s.length() && Count.containsKey(s.charAt(right)) == true) {
                Count.put(s.charAt(right), Count.get(s.charAt(right)) - 1);
                if(Count.get(s.charAt(right)) >= 0) {
                    flag--;
                }
            }
            if(flag == 0) {
                ans.add(left);
            }
        }

        return ans;
    }
}