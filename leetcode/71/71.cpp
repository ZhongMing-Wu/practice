class Solution {
public:
    string simplifyPath(string path) {
        vector<string> requiredWords;
        int pre = 0, current;
        for(current = 1; current < path.size(); current++) {
            if(path[current] != '/') {
                continue;
            }
            else {
                string partStr = path.substr(pre, current - pre);
                tackleStr(requiredWords, partStr);  
            }
            pre = current;
        }
        if(pre != path.size() - 1) {
            tackleStr(requiredWords, path.substr(pre));
        }
        string strAns = "";
        for(int i = 0; i < requiredWords.size(); i++) {
            strAns += requiredWords[i];
        }
        if(strAns == "")
            return "/";
        return strAns;
    }

    void tackleStr(vector<string>& requiredWords, string partStr) {
        if(partStr.size() == 1 && partStr[0] == '/')
            return;
        string content = partStr.substr(1);
        if(content == "..") {
            if(requiredWords.size() != 0) {
                requiredWords.pop_back();
            }
        }
        else if(content != ".") {
            requiredWords.push_back(partStr);
        }
    }
};