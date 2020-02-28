class Solution {
public:
	bool isScramble(string s1, string s2) {
		if (s1.size() != s2.size())
			return false;
		if (s1 == s2)
			return true;
		if (s1.size() == 1)
			return false;
		string ss1 = s1;
        string ss2 = s2;
        sort(ss1.begin(), ss1.end());
        sort(ss2.begin(), ss2.end());
        if(ss1 != ss2)
            return false;
		for (int len = 1; len < s1.size(); len++) {
			if (isScramble(s1.substr(0, len), s2.substr(0, len)) && isScramble(s1.substr(len), s2.substr(len))) {
				return true;
			}

			if (isScramble(s1.substr(0, len), s2.substr(s2.size() - len)) && isScramble(s1.substr(len), s2.substr(0, s2.size() - len))) {
				return true;
			}
		}
		return false;
	}
};