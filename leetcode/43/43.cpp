class Solution {
public:
    string multiply(string num1, string num2) {
        unordered_map<char, string> storageAns;
        if(num1 == "0" || num2 == "0") 
            return "0";
        int k = 0;
        string partAns;
        string ans = "";
        for(int i = num2.size() - 1; i >= 0; i--, k++) {
            partAns = "";
            if(storageAns[num2[i]].size() != 0) {
                partAns = storageAns[num2[i]];
            }
            else if(num2[i] == '0')
                continue;
            else {
                for(char j = '0'; j < num2[i]; j++) {
                    partAns = addTwoString(partAns, num1);
                }
                storageAns[num2[i]] = partAns;
            }
            for(int j = 0; j < k; j++)
                partAns += '0';
            ans = addTwoString(ans, partAns);
        }
        return ans;
    }
    string addTwoString(string num1, string num2) {
        int i = num1.size() - 1;
        int j = num2.size() - 1;
        int flag = 0;  //用于表示两数相加是否有进位
        int integer1, integer2;
        string ans = "";
        while(i >= 0 && j >= 0) {
            integer1 = num1[i] - '0';
            integer2 = num2[j] - '0';
            ans = to_string((integer1 + integer2 + flag) % 10) + ans;
            flag = (integer1 + integer2 + flag) / 10;
            i--;
            j--;
        }
        while(i >= 0) {
            integer1 = num1[i] - '0';
            ans = to_string((integer1 + flag) % 10) + ans;
            flag = (integer1 + flag) / 10;
            i--;
        }
        while(j >= 0) {
            integer2 = num2[j] - '0';
            ans = to_string((integer2 + flag) % 10) + ans;
            flag = (integer2 + flag) / 10;
            j--;
        }
        if(flag) {
            ans = "1" + ans;
        }
        return ans;
    }
};