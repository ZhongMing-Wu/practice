class Solution {
public:
    vector<string> restoreIpAddresses(string s) {
        string part1, part2, part3, part4;
        set<string> ansSet;
        map<string, int> visited;
        for(int one = 1; one <= 3; one++) {
            for(int two = 1; two <= 3; two++) {
                for(int three = 1; three <= 3; three++) {
                    for(int four = 1; four <= 3; four++) {
                        if(one + two + three + four == s.size()) {
                            part1 = s.substr(0, one);
                            part2 = s.substr(one, two);
                            part3 = s.substr(one + two, three);
                            part4 = s.substr(one + two + three, four);
                            if(judge(part1) && judge(part2) && judge(part3) && judge(part4)) {
                                ansSet.insert(part1 + "." + part2 + "." + part3 + "." + part4);
                            }
                        }
                    }
                }
            }
        }
        vector<string> ansVector(ansSet.begin(), ansSet.end());
        return ansVector;
    }
    int judge(string& s) {
        if(stoi(s) > 255 || stoi(s) < 0)
            return 0;
        if(s[0] == '0' && s.size() != 1)
            return 0;
        return 1;
    }
};