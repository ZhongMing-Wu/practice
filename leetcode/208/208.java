class Trie {

    /** Initialize your data structure here. */
    private TrieTreeNode root;
    public Trie() {
        root = new TrieTreeNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieTreeNode currentNode = root;
        int flag;
        for(int i = 0; i < word.length(); i++) {
            flag = 0;
            for(int j = 0; j < currentNode.getChildNodes().size(); j++) {
                if(word.charAt(i) == currentNode.getChildNodes().get(j).getCh()) {
                    currentNode =  currentNode.getChildNodes().get(j);
                    flag = 1;
                    break;
                }
            }
            if(flag == 0) {
                TrieTreeNode newNode = new TrieTreeNode();
                newNode.setCh(word.charAt(i));
                currentNode.getChildNodes().add(newNode);
                currentNode = newNode;
            }
        }
        currentNode.setIsExisted(true);
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieTreeNode currentNode = root;
        int flag;
        for(int i = 0; i < word.length(); i++) {
            flag = 0;
            for(int j = 0; j < currentNode.getChildNodes().size(); j++) {
                if(word.charAt(i) == currentNode.getChildNodes().get(j).getCh()) {
                    currentNode =  currentNode.getChildNodes().get(j);
                    flag = 1;
                    break;
                }
            }
            if(flag == 0) {
                return false;
            }
        }
        if(currentNode.getIsExisted() == true) {
            return true;
        }
        else {
            return false;
        }
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieTreeNode currentNode = root;
        int flag;
        for(int i = 0; i < prefix.length(); i++) {
            flag = 0;
            for(int j = 0; j < currentNode.getChildNodes().size(); j++) {
                if(prefix.charAt(i) == currentNode.getChildNodes().get(j).getCh()) {
                    currentNode =  currentNode.getChildNodes().get(j);
                    flag = 1;
                    break;
                }
            }
            if(flag == 0) {
                return false;
            }
        }
        return true;
    }
}

class TrieTreeNode {
    private boolean isExisted;
    private char ch;
    List<TrieTreeNode> childNodes;

    public TrieTreeNode() {
        isExisted = false;
        ch = ' ';
        childNodes = new ArrayList<>();
    }

    public boolean getIsExisted() {
        return isExisted;
    }

    public void setIsExisted(boolean isExisted) {
        this.isExisted = isExisted;
    }

    public char getCh() {
        return ch;
    }

    public void setCh(char ch) {
        this.ch = ch;
    }

    public List<TrieTreeNode> getChildNodes() {
        return childNodes;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */