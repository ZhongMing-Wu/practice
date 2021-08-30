class Solution {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> ans = new ArrayList<>();
        Trie root = new Trie();
        int[] records;
        Arrays.sort(words, (String w1, String w2) -> {
            return w1.length() - w2.length();
        });
        for(String word : words) {
            records = new int[word.length()];
            if(search(word, 0, root, 0, records)) {
                ans.add(word);
            }
            addWord(word, root);
        }
        return ans;
    }

    public boolean search(String word, int index, Trie root, int num, int[] records) {
        int len = word.length();
        if(index == len) {
            return num > 1 ? true : false;
        }
        
        if(records[index] == -1) {
            return false;
        }
        Trie preNode = root, nowNode = null;
        for(int i = index; i < len; i++) {
            nowNode = preNode.children[word.charAt(i) - 'a'];
            if(nowNode == null) {
                records[index] = -1;
                return false;
            }
            if(nowNode.isEnd && search(word, i + 1, root, num + 1, records)) {
                return true;
            }
            preNode = nowNode;
        }
        records[index] = -1;
        return false;
    }

    public void addWord(String word, Trie root) {
        Trie curNode = root;
        for(int i = 0; i < word.length(); i++) {
            if(curNode.children[word.charAt(i) - 'a'] == null) {
                curNode.children[word.charAt(i) - 'a'] = new Trie();
            }
            curNode = curNode.children[word.charAt(i) - 'a'];
        }
        curNode.isEnd = true;
    }
}

class Trie {
    public boolean isEnd;
    public Trie[] children;
    public Trie() {
        isEnd = false;
        children = new Trie[26];
    }
}