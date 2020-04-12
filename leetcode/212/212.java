class Solution {
    private List<TrieTree> headCharacters;
    private StringBuilder ans;
    private List<String> anses;
    private int[][] visited;
    public List<String> findWords(char[][] board, String[] words) {
        if(words.length == 0 || board.length == 0) {
            return new ArrayList<String>();
        }
        headCharacters = new ArrayList<TrieTree>();
        ans = new StringBuilder();
        anses = new ArrayList<String>();
        visited = new int[board.length][board[0].length];
        String word = null;
        for(int i = 0; i < words.length; i++) {
            word = words[i];
            buildTrieTree(0, word, headCharacters);
        }
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                startFind(board, i, j, headCharacters);
            }
        }
        return anses;
    }
    public void buildTrieTree(int index, String word, List<TrieTree> curList) {
        char curValue = word.charAt(index);
        TrieTree tempNode = null;
        for(int i = 0; i < curList.size(); i++) {
            tempNode = curList.get(i);
            if(curValue == tempNode.getValue()) {
                if(index == word.length() - 1) {
                    tempNode.setEnd();
                    return;
                }
                else {
                    buildTrieTree(index + 1, word, tempNode.getNextCharacters());
                    return;
                }
            }
        }
        List<TrieTree> tempList = curList;
        TrieTree newNode = null;
        while(index < word.length()) {
            curValue = word.charAt(index++);
            newNode = new TrieTree(curValue);
            tempList.add(newNode);
            tempList = newNode.getNextCharacters();
        }
        newNode.setEnd();
    }
    void startFind(char[][] board, int row, int col, List<TrieTree> curList) {
        char curValue = board[row][col];
        visited[row][col] = 1;
        TrieTree tempNode = null;
        int rowLen = board.length, colLen = board[0].length;
        for(int i = 0; i < curList.size(); i++) {
            tempNode = curList.get(i);
            if(curValue == tempNode.getValue()) {
                ans.append(curValue);
                if(tempNode.getEnd() && !anses.contains(ans.toString())) {
                    anses.add(ans.toString());
                }
                if(row > 0 && visited[row - 1][col] == 0) {
                    startFind(board, row - 1, col, tempNode.getNextCharacters());
                }
                if(row < rowLen - 1 && visited[row + 1][col] == 0) {
                    startFind(board, row + 1, col, tempNode.getNextCharacters());
                }
                if(col > 0 && visited[row][col - 1] == 0) {
                    startFind(board, row, col - 1, tempNode.getNextCharacters());
                } 
                if(col < colLen - 1 && visited[row][col + 1] == 0) {
                    startFind(board, row, col + 1, tempNode.getNextCharacters());
                }
                visited[row][col] = 0;
                ans.deleteCharAt(ans.length() - 1);
                return;
            }
        }
        visited[row][col] = 0;
    }
}

class TrieTree {
    private boolean end;
    private List<TrieTree> nextCharacters;
    private char value;
    public TrieTree(char value) {
        end = false;
        this.value = value;
        nextCharacters = new ArrayList<TrieTree>();
    }
    public List<TrieTree> getNextCharacters() {
        return nextCharacters;
    }
    public char getValue() {
        return value;
    }
    public boolean getEnd() {
        return end;
    }
    public void setEnd() {
        end = true;
    }
}