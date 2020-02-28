class Solution {
public:
    bool isInterleave(string s1, string s2, string s3) {
        if(s1.size() + s2.size() != s3.size())
            return false;
        if(!s1.size()) {
            if(s2 == s3) {
                return true;
            }
            else {
                return false;
            }
        }
        if(!s2.size()) {
            if(s1 == s3) {
                return true;
            }
            else {
                return false;
            }
        }
        if(!s3.size()) {
            return false;
        }
        vector<int> results(s2.size() + 1);
        for(int i = 0; i <= s1.size(); i++) {
            for(int j = 0; j <= s2.size(); j++) {
                if(i == 0 && j == 0) {
                    results[j] = 1;
                }
                else if(i == 0 && j != 0) {
                    if(results[j - 1] && s2[j - 1] == s3[j - 1]) {
                        results[j] = 1;
                    }
                    else {
                        results[j] = 0;
                    }
                }
                else if(i != 0 && j == 0) {
                    if(results[j] && s1[i - 1] == s3[i - 1]) {
                        results[j] = 1;
                    }
                    else {
                        results[j] = 0;
                    }
                }
                else {
                    if(results[j - 1] && s2[j - 1] == s3[i + j - 1]) {
                        results[j] = 1;
                    }
                    else if(results[j] && s1[i - 1] == s3[i + j - 1]) {
                        results[j] = 1;
                    }
                    else {
                        results[j] = 0;
                    }
                }
            }
        }
        if(results[s2.size()]) {
            return true;
        }
        else {
            return false;
        }
    }
};