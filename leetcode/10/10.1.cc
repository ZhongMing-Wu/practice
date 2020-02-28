class Solution {
public:
	bool isMatch(string s, string p) {
		if (!p.size())
			return s.size() > 0 ? false : true;
		if (!s.size()) {
			if (2 <= p.size() && p[1] == '*')
				return isMatch(s, p.substr(2));
			else
				return false;
		}
		else {
			bool firstFlag;
			if (p[0] == s[0] || p[0] == '.')
				firstFlag = true;
			else
				firstFlag = false;
			if (2 <= p.size() && p[1] == '*') {
				return (firstFlag && isMatch(s.substr(1), p)) || isMatch(s, p.substr(2));
			}
			else
				return firstFlag && isMatch(s.substr(1), p.substr(1));
		}
	}
};