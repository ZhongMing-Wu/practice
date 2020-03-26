class Solution {
    Map<String, List<String>> toNextWords;
    Map<String, Integer> visited;
    LinkedList<String> queue;
    List<List<String>> ans;
    int level, minLevel;
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        for(int i = 0; i < wordList.size(); i++) {
            if(endWord.equals(wordList.get(i))) {
                break;
            }
            else if(i == wordList.size() - 1) {
                ans = new ArrayList<List<String>>();
                return ans;
            }
        }
        level = 1;
        minLevel = Integer.MAX_VALUE;
        ans = new ArrayList<List<String>>();
        toNextWords = new HashMap<String, List<String>>();
        visited = new HashMap<String, Integer>();
        queue = new LinkedList<String>();
        queue.offerFirst(beginWord);
        String temp = null;
        int queueLen,dictLen = wordList.size();
        List<String> nextWords;
        while( !queue.isEmpty() ) {
            if(level > minLevel) {
                break;
            }
            queueLen = queue.size();
            for(int i = 0; i < queueLen; i++) {
                visited.put(queue.get(i), 1);
            }
            for(int i = 0; i < queueLen; i++) {
                temp = queue.pollFirst();
                nextWords = new ArrayList<>();
                String strInDict;
                toNextWords.put(temp, nextWords);
                for(int j = 0; j < dictLen; j++) {
                    strInDict = wordList.get(j);
                    if( !visited.containsKey(strInDict) ) {
                        if( isNext(temp, strInDict) ) {
                            if( strInDict.equals(endWord) ) {
                                minLevel = level;
                            }
                            toNextWords.get(temp).add(strInDict);
                            if( !queue.contains(strInDict) ) {
                                queue.offerLast(strInDict);
                            }
                        }
                    }
                }
            }
            level++;
        }
        if(minLevel > level) {
            return ans;
        }
        List<String> oneAns = new ArrayList<>();
        for(int i = 0; i < level; i++) {
            oneAns.add(" ");
        }
        DFS(beginWord, oneAns, 0, endWord);
        return ans;
    }
    
    boolean isNext(String curWord, String nextWord) {
        int diffTime = 0;
        for(int i = 0; i < curWord.length(); i++) {
            if(curWord.charAt(i) != nextWord.charAt(i)) {
                diffTime++;
                if(diffTime > 1) {
                    return false;
                } 
            }
        }
        return true;
    }
    
    void DFS(String curWord, List<String> oneAns, int level, String endWord) {
        List<String> nextWords = toNextWords.get(curWord);
        oneAns.set(level, curWord);
        if(level == minLevel - 1) {
            if(nextWords.contains(endWord)) {
                List<String> tempList = new ArrayList<>(oneAns);
                tempList.set(minLevel, endWord);
                ans.add(tempList);
            }
            return;
        }
        if(level < minLevel) {
            for(int i = 0; i < nextWords.size(); i++) {
                DFS(nextWords.get(i), oneAns, level + 1, endWord);
            }
        }
    }
}