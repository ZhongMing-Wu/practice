class Solution {
public:
    int ladderLength(string beginWord, string endWord, vector<string>& wordList) {
        unordered_set<string> newWordList(wordList.begin(),wordList.end());
        newWordList.insert(beginWord);
        queue<string> _queue;
        _queue.push(beginWord);
        _distance[beginWord]=1;
        while(!_queue.empty()){
            string frontWord=_queue.front();
            _queue.pop();
            if(frontWord==endWord)
                break;
            _visited[frontWord]=1;
            
            for(int i=0;i<frontWord.size();i++){
                string newWord=frontWord;
                for(char c='a';c<='z';c++){
                    newWord[i]=c;
                    if(_visited[newWord]){
                        continue;
                    }
                    if(newWordList.count(newWord)){
                        if(_distance[newWord]==0){
                            _distance[newWord]=_distance[frontWord]+1;
                            _queue.push(newWord);
                        }
                    }
                }
            }
        }
        return _distance[endWord];
    }
private:
    unordered_map<string,int> _visited;
    unordered_map<string,int> _distance;   //距离为0表示相距无穷大
};