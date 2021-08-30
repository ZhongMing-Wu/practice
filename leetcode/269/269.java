class Solution {
    HashMap<Character, Integer> in = null;
    HashMap<Character, Set<Character>> toNeighbors = null;
    public String alienOrder(String[] words) {
        in = new HashMap<>();
        toNeighbors = new HashMap<>();
        int[] occurCharacter = new int[26];
        for(String word : words) {
            for(int i = 0; i < word.length(); i++) {
                occurCharacter[word.charAt(i) - 'a'] = 1;
            }
        }
        int occurCount = 0;
        for(int i = 0; i < 26; i++) {
            occurCount += occurCharacter[i];
            if(occurCharacter[i] == 1) {
                in.put((char)('a' + i), 0);
            }
        }
        int len = words.length;
        for(int i = 0; i < len; i++) {
            for(int j = i + 1; j < len; j++) {
                if(!compareStr(words[i], words[j])) {
                    return "";
                }
            }
        }

        PriorityQueue<Character> pQueue = new PriorityQueue<>((Character c1, Character c2) -> {
            return c1 - c2;
        });
        for(Map.Entry<Character, Integer> entry : in.entrySet()) {
            if(entry.getValue() == 0) {
                pQueue.add(entry.getKey());
            }
        }
        StringBuilder sb = new StringBuilder();
        while(!pQueue.isEmpty()) {
            char c = pQueue.poll();
            sb.append(c);
            Set<Character> neighbors = toNeighbors.getOrDefault(c, null);
            if(neighbors == null) {
                continue;
            }
            for(char neighbor : neighbors) {
                in.put(neighbor, in.get(neighbor) - 1);
                if(in.get(neighbor) == 0) {
                    pQueue.add(neighbor);
                }
            }
        }
        if(sb.length() < occurCount) {
            return  "";
        }
        return sb.toString();
    }

    public boolean compareStr(String word1, String word2) {
        int len = Math.min(word1.length(), word2.length()), i = 0;
        for(; i < len; i++) {
            char c1 = word1.charAt(i);
            char c2 = word2.charAt(i);
            if(c1 != c2) {
                if(!toNeighbors.containsKey(c1)) {
                    toNeighbors.put(c1, new HashSet<>());
                }
                Set<Character> neighbor = toNeighbors.get(c1);
                if(!neighbor.contains(c2)) {
                    neighbor.add(c2);
                    in.put(c2, in.getOrDefault(c2, 0) + 1);
                }
                break;
            }
        }
        if(i == len && word1.length() > len) {
            return false;
        }
        return true;
    }
}