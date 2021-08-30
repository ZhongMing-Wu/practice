class Solution {
    public String reorganizeString(String s) {
        HashMap<Character, Integer> toCount = new HashMap<>();
        List<Character> chars = new ArrayList<>();
        int n = s.length();
        for(int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if(!toCount.containsKey(c)) {
                chars.add(c);
            }
            toCount.put(c, toCount.getOrDefault(c, 0) + 1);
            if(toCount.get(c) > (n + 1) / 2) {
                return "";
            }
        }
        char[] reorganized = new char[n];
        int evenIndex = 0, oddIndex = 1;
        for(char c : chars) {
            int count = toCount.get(c);
            if(count == (n + 1) / 2 && n % 2 == 1) {
                for(int i = 0; i < count; i++) {
                    reorganized[evenIndex] = c;
                    evenIndex += 2;
                }
                continue;
            }
            for(int i = 0; i < count; i++) {
                if(oddIndex < n) {
                    reorganized[oddIndex] = c;
                    oddIndex += 2;
                } else {
                    reorganized[evenIndex] = c;
                    evenIndex += 2;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(char c : reorganized) {
            sb.append(c);
        }
        return sb.toString();
    }
}