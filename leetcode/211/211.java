class WordDictionary {
    List<Node> headNodes;
    /** Initialize your data structure here. */
    public WordDictionary() {
        headNodes = new ArrayList<Node>();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        startAddWord(word, 0, headNodes);
    }
    public void startAddWord(String word, int index, List<Node> headNodes) {
        char letter = word.charAt(index);
        for(int i = 0; i < headNodes.size(); i++) {
            if(letter == headNodes.get(i).getVal()) {
                if(index == word.length() -  1) {
                    headNodes.get(i).setIsEnd();
                    return;
                }
                startAddWord(word, index + 1, headNodes.get(i).getList());
                return;
            }
        }
        Node newNode = null;
        List<Node> curList = headNodes;
        for(;index < word.length(); index++) {
            newNode = new Node(word.charAt(index));
            curList.add(newNode);
            curList = newNode.getList();
        }
        newNode.setIsEnd();
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return startSearch(word, 0, headNodes);
    }
    public boolean startSearch(String word, int index, List<Node> headNodes) {
        char letter = word.charAt(index);
        boolean flag = false;
        if(letter == '.') {
            if(index == word.length() - 1) {
                for(int i = 0; i < headNodes.size(); i++) {
                    if(headNodes.get(i).getIsEnd()) {
                        return true;
                    }
                }
                return false;
            }
            for(int i = 0; i < headNodes.size(); i++) {
                flag = startSearch(word, index + 1, headNodes.get(i).getList());
                if(flag) {
                    return flag;
                }
            }
            return flag;
        }
        for(int i = 0; i < headNodes.size(); i++) {
            if(letter == headNodes.get(i).getVal()) {
                if(index == word.length() - 1) {
                    return headNodes.get(i).getIsEnd();
                }
                flag = startSearch(word, index + 1, headNodes.get(i).getList());
            }
        }
        return flag;
    }
}

public class Node {
    private char val;
    private List<Node> list;
    private boolean isEnd;
    public Node(char val) {
        this.val = val;
        list = new ArrayList<Node>();
        isEnd = false;
    }
    public char getVal() {
        return val;
    }
    public List<Node> getList() {
        return list;
    }
    public boolean getIsEnd() {
        return isEnd;
    }
    public void setIsEnd() {
        isEnd = true;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */