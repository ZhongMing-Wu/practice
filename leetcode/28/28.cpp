class Solution {
public:
    int strStr(string haystack, string needle) {
        if(needle.size() == 0) 
            return 0;
        if(haystack.size() == 0)
            return -1;
        vector<int> next;
        next.resize(needle.size() + 1);
        KMP(needle, next);
        int i = 0, j = 0;
        while(i < haystack.size() && j < needle.size()) {
            if(haystack[i] == needle[j]) {
                ++i;
                ++j;
            }
            else {
                j = next[j+1];
                if(j == 0) {
                    ++i;
                }
                else {
                    --j;
                }
            }
        }
        if(j == needle.size()) {
            return i-j;
        }
        else {
            return -1;
        }
    }
    void KMP(string needle, vector<int> &next) {
        int i = 1;
        int j = 0;
        next[1] = 0;
        while(i < needle.size()) {
            if(j == 0 || needle[i-1] == needle[j-1]) 
                next[++i] = ++j;
            else
                j = next[j];
            
        }
    }
};