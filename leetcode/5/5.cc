class Solution {
public:
	string longestPalindrome(string s) {
		if (s == ""||s.size()==1)
			return s;
		int qualifiedMaxLen = 1, restrictedLen = s.size()+1;
		int testLen;
		string ans = "";
		while (qualifiedMaxLen + 1 != restrictedLen) {
			testLen = (restrictedLen - qualifiedMaxLen) / 2 + qualifiedMaxLen;
			string testStr, reverseStr;
			int flag = false;
			for (int start = 0; start + testLen - 1 < s.size(); start++) {
				testStr = s.substr(start, testLen);
				reverseStr = testStr;
				reverse(reverseStr.begin(), reverseStr.end());
				if (reverseStr == testStr) {
					ans = testStr;
					qualifiedMaxLen = testLen;
					flag = true;
				}
                if(start+testLen<s.size()&&testLen+1<restrictedLen){
                    testStr = s.substr(start, testLen+1);
                    reverseStr = testStr;
                    reverse(reverseStr.begin(), reverseStr.end());
                    if (reverseStr == testStr) {
					  ans = testStr;
					  qualifiedMaxLen = testLen;
					  flag = true;
                    }
                }
                if(flag)
                    break;
			}
			if(!flag)
			    restrictedLen = testLen;
		}
		if (ans == "")
			ans = s[0];
		return ans;
	}
};