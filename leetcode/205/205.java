class Solution {
    public boolean isIsomorphic(String s, String t) {
        HashMap<Character, Character> toMapChar1 = new HashMap<>();
        HashMap<Character, Character> toMapChar2 = new HashMap<>();
        for(int i = 0; i < s.length(); i++) {
            char ch1 = s.charAt(i);
            char ch2 = t.charAt(i);
            if(toMapChar1.containsKey(ch1) && toMapChar1.get(ch1) != ch2) {
                return false;
            }

            if(toMapChar2.containsKey(ch2) && toMapChar2.get(ch2) != ch1) {
                return false;
            }

            toMapChar1.put(ch1, ch2);
            toMapChar2.put(ch2, ch1);
        }
        return true;
    }
}