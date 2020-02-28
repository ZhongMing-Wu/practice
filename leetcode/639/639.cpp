class Solution {
public:
    int numDecodings(string s) {
        if(s.size() == 0)
            return 0;
        vector<long long> Num;  //用于存储s中从0到i的子串的解码方式的总数
        Num.resize(s.size() + 1);
        Num[0] = 1;  //对于空子串设置其解码方式总数为1，方便后面的计算
        if(s[0] == '*') {
            Num[1] = 9;
        }
        else if(s[0] == '0') {
            Num[1] = 0;
        }
        else {
            Num[1] = 1;
        }
        for(int i = 2; i <= s.size(); i++) {
            if(s[i - 1] == '*') {
                Num[i] = (Num[i - 1] * 9 + Num[i - 2] * findQualifiedNum(s[i - 2], s[i - 1])) % (1000000000 + 7);
            }
            else {
                if(s[i - 1] == '0') {
                    Num[i] = (Num[i - 2] * findQualifiedNum(s[i - 2], s[i - 1])) % (1000000000 + 7);   
                }
                else {
                    Num[i] = (Num[i - 1] + Num[i - 2] * findQualifiedNum(s[i - 2], s[i - 1])) % (1000000000 + 7);
                }
            }
        }
        return Num[s.size()];
    }

    int findQualifiedNum(char c1, char c2) {     //返回一个二位字符串的解码方式的总数
        if(c1 != '*' && c2 != '*') {
            if((c1 - '0') * 10 + c2 - '0' < 27  && c1 != '0') {
                return 1;
            }
            else {
                return 0;
            }
        }
        else if(c1 == '*' && c2 == '*') {
            return 15;
        }
        else if(c1 == '*') {
            if(c2 <= '6') {
                return 2;
            }
            else {
                return 1;
            }
        }
        else {
            if(c1 == '1') {
                return 9;
            }
            else if(c1 == '2') {
                return 6;
            }
            else {
                return 0;
            }
        }
    }
};