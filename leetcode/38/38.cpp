class Solution {
public:
    string countAndSay(int n) {
        string ans = "1";
        if(n == 1)
            return ans;
        else {
            for(int i = 2; i <= n; i++) {
                ans = say(ans);
            }
        }
        return ans;
    }
    
    string say(string lastSay) {
        string ans = "";
        int left = 0, right = 0;
        while(right < lastSay.size()) {
            while(lastSay[left] == lastSay[right]) {
                right++;
                if(right == lastSay.size())
                    break;
            }
            ans += to_string(right - left)+lastSay[left];
            left = right;
        }
        return ans;
    }
};