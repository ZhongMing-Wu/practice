class Solution {
public:
    vector<int> findSubstring(string s, vector<string>& words) {
        vector<int> ans;
        if(words.size() == 0 || words.size() * words[0].size() > s.size()) 
            return ans;
        unordered_map<string, int> handleExist;
        unordered_map<string, int> recordExist;
        for(int i = 0; i < words.size(); i++)
            recordExist[words[i]]++;
        int requiredTotalLength = words.size() * words[0].size();
        int oneWordLength = words[0].size();
        int left, right;
        int count;
        for(int i = 0; i < words[0].size(); i++) {
            count = 0;
            left = right = i;
            string rightStr, leftStr;
            handleExist = recordExist;
            while(right + oneWordLength <= s.size()){
                rightStr = s.substr(right, oneWordLength);
                if(handleExist[rightStr] == 0) {
                    if(left == right) {
                        left += oneWordLength;
                        right += oneWordLength;
                    }
                    else {
                        leftStr = s.substr(left, oneWordLength);
                        handleExist[leftStr]++;
                        left += oneWordLength;
                        count--;
                    }
                }
                else {
                    handleExist[rightStr]--;
                    count++;
                    if(count == words.size()) {
                        ans.push_back(left);
                        leftStr = s.substr(left, oneWordLength);
                        handleExist[leftStr]++;
                        left += oneWordLength;
                        count--;
                    }
                    right += oneWordLength;
                }
            }
        }
        return ans;
    }
};