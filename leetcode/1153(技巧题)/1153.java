class Solution {
    public boolean canConvert(String str1, String str2) {
        if(str1.equals(str2)) {
            return true;
        }
        HashMap<Character, Character> aim = new HashMap<>();
        HashSet<Character> charSet = new HashSet<>();
        for(int i = 0; i < str1.length(); ++i) {
            char c1 = str1.charAt(i);
            char c2 = str2.charAt(i);
            if(aim.containsKey(c1)) {
                if(aim.get(c1) != c2) {
                    return false;
                }
            } else {
                aim.put(c1, c2);
                charSet.add(c2);
            }
        }
        if(charSet.size() < 26) {
            return true;
        }
        return false;
    }
}