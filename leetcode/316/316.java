class Solution {
    public String removeDuplicateLetters(String s) {
        if(0 == s.length()) {
            return "";
        }
        int[] existed = new int[26];
        int[] count = new int[26];
        Deque<Character> stack = new LinkedList<>();

        for(int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }

        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int index = c - 'a';
            if(1 == existed[index]) {
                count[index]--;
                continue;
            }

            while(0 != stack.size() && stack.peek() > c && 0 != count[stack.peek() - 'a']) {
                char temp = stack.pop();
                existed[temp - 'a'] = 0;
            }

            stack.push(c);
            count[c - 'a']--;
            existed[c - 'a'] = 1;
        }

        StringBuilder sb = new StringBuilder();
        while(0 != stack.size()) {
            sb.append(stack.pop());
        }

        return sb.reverse().toString();
    }
}