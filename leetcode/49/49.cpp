class Solution {
public:
    vector<vector<string>> groupAnagrams(vector<string>& strs) {
        vector<int> records; // 记录一个单词中字母出现的次数
        records.resize(26);
        vector<vector<string>> ans;
        unordered_map<string, int> toLocation;
        string strTrait;
        for(int i = 0; i < strs.size(); i++) {
            strTrait = trait(records, strs[i]);
            if(toLocation[strTrait]) {
                ans[toLocation[strTrait] - 1].push_back(strs[i]);
            }
            else {
                vector<string> newAns;
                newAns.push_back(strs[i]);
                ans.push_back(newAns);
                toLocation[strTrait] = ans.size();
            }
        }
        return ans;
    }
    string trait(vector<int>& records, string testStr) {
        for(int i = 0; i < testStr.size(); i++) {
            records[testStr[i] - 'a']++;
        }
        string strTrait = "";
        for(int i = 0; i < records.size(); i++) {
            strTrait += ('0' + records[i]);
            records[i] = 0;
        }
        return strTrait;
    }
};