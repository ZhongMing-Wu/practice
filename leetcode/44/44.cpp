class Solution {
public:
    bool isMatch(string s, string p) {
        int ns = s.size() + 1;
        int np = p.size() + 1;
        vector<vector<int>> records;
        records.resize(ns);
        for(int i = 0; i < ns; i++)
            records[i].resize(np);
        records[0][0] = 1;
        for(int i = 0; i < ns; i++) {
            for(int j = 1; j < np; j++) {
                if(i > 0 && (p[j-1] == s[i-1] || p[j-1] == '?'))
                    records[i][j] = records[i-1][j-1];
                if(p[j-1] == '*') {
                    if(i == 0) 
                        records[i][j] = records[i][j-1];
                    else
                        records[i][j] = records[i][j-1] || records[i-1][j];
                }
            }
        }
        return records[ns-1][np-1] == 1 ? true : false;
    }
};