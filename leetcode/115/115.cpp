class Solution {
public:
    int numDistinct(string s, string t) {
        int tLen = t.size();
        vector<long long> count(t.size() + 1);
        count[0] = 1;
        for(int i = s.size() - 1; i >= 0; i--) {
            for(int j = 0; j < t.size(); j++) {
                if(s[i] == t[j]) {
                    count[tLen - j] += count[tLen - j - 1];                   
                }
            }
        }
        return (int)count[tLen];
    }
};