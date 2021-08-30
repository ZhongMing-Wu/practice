class AutocompleteSystem {
    HashMap<String, Integer> toHotDegree;
    Trie root;
    StringBuilder sb;
    public AutocompleteSystem(String[] sentences, int[] times) {
        toHotDegree = new HashMap<>();
        root = new Trie();
        sb = new StringBuilder();
        for(int i = 0; i < sentences.length; i++) {
            toHotDegree.put(sentences[i], times[i]);
            addSentence(root, sentences[i], 0);
        }
    }
    
    public List<String> input(char c) {
        if(c == '#') {
            String newStr = sb.toString();
            if(!toHotDegree.containsKey(newStr)) {
                addSentence(root, newStr, 0);
            }
            toHotDegree.put(newStr, toHotDegree.getOrDefault(newStr, 0) + 1);
            sb.delete(0, sb.length());
            return new ArrayList<>();
        }

        sb.append(c);
        List<String> matchedSentence = new ArrayList<>(findMatchSentences(root, sb.toString()));
        matchedSentence.sort((String s1, String s2) -> {
            if(toHotDegree.get(s1) != toHotDegree.get(s2)) {
                return toHotDegree.get(s2) - toHotDegree.get(s1);
            }
            return s1.compareTo(s2);
        });

        List<String> expectedSentence = new ArrayList<>();
        for(int i = 0; i < Math.min(3, matchedSentence.size()); i++) {
            expectedSentence.add(matchedSentence.get(i));
        }
        return expectedSentence;
    }

    public void addSentence(Trie node, String sentence, int index) {
        if(index == sentence.length()) {
            return;
        }
        char c = sentence.charAt(index);
        int characterIndex = c == ' ' ? 26 : c - 'a';
        if(node.children[characterIndex] == null) {
            node.children[characterIndex] = new Trie();
        }
        node.children[characterIndex].strs.add(sentence);
        addSentence(node.children[characterIndex], sentence, index + 1);
    }

    public List<String> findMatchSentences(Trie node, String prefix) {
        for(int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            int characterIndex = c == ' ' ? 26 : c - 'a';
            if(node.children[characterIndex] == null) {
                return new ArrayList<>();
            }
            node = node.children[characterIndex];
        }
        return node.strs;
    }
}

class Trie {
    public Trie[] children;
    public List<String> strs;

    public Trie() {
        strs = new ArrayList<>();
        children = new Trie[27];
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */