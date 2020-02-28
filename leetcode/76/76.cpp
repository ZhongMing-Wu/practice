class Solution {
public:
	string minWindow(string s, string t) {
        if(!s.size() || !t.size())
            return "";
		int notFind = t.size();  //表示还未找到的字母的个数
		map<char, int> characterCount;
		for (int i = 0; i < t.size(); i++) {
			characterCount[t[i]]++;
		}
		string minStr = "";
		int left = 0, right = 0;
		for (; right <= s.size(); right++) {
			if (right < s.size() && notFind && characterCount[s[right]]) {
				if (characterCount[s[right]] > 0)
					notFind--;
				characterCount[s[right]]--;
				if (characterCount[s[right]] == 0)
					characterCount[s[right]] = -1;
			}
			else if (notFind == 0) {
				while (notFind == 0) {
					if (characterCount[s[left]]) {
						//characterCount[s[left]] + 1表示某个字母额外出现的次数
						if (characterCount[s[left]] + 1 == 0) {  //为0表示没有额外出现
							notFind++;
							characterCount[s[left]] = 1;
						}
						else {
							characterCount[s[left]] += 1;
							left++;
						}
					}
					else {
						left++;
					}
				}
                if(minStr.size() == 0 || minStr.size() > right - left)
				    minStr = s.substr(left, right - left);
				left++;
				right--;
			}
		}
		return minStr;
	}
};