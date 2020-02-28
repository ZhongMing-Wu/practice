class Solution {
public:
    int numDecodings(string s) {
        vector<int> ans;
        ans.resize(s.size() + 1);  //ans[i] 中存储s前i个字符构成的子串的解码方法总数
        ans[0] = 1;
        if(s[0] == '0')
            ans[1] = 0;
        else
            ans[1] = 1;
        for(int i = 2; i <= s.size(); i++) {
            ans[i] += s[i - 1] == '0' ? 0 : ans[i - 1];
            int temp = stoi(s.substr(i - 2, 2));
            ans[i] += (temp <= 26 && temp >= 10) ? ans[i - 2] : 0;
        }
        return ans[s.size()];
    }
};