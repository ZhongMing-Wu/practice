class Solution {
public:
    vector<string> fullJustify(vector<string>& words, int maxWidth) {
        int wordsCount = 0;
        int leftSpace = maxWidth;
        vector<string> oneRowWords;
        vector<string> ans;
        int isTail = 0;
        for(int i = 0; i < words.size(); i++) {
            if(i == words.size() - 1) {
                isTail = 1;
            }
            leftSpace -= words[i].size();
            if(leftSpace > wordsCount) {
                wordsCount++;
                oneRowWords.push_back(words[i]);
            }
            else if(leftSpace == wordsCount) {
                wordsCount++;
                oneRowWords.push_back(words[i]);
                ans.push_back(createOneRowString(oneRowWords, leftSpace, isTail));
                oneRowWords.clear();
                leftSpace = maxWidth;
                wordsCount = 0;
            }
            else {
                leftSpace += words[i].size();
                ans.push_back(createOneRowString(oneRowWords, leftSpace, 0));
                oneRowWords.clear();
                leftSpace = maxWidth;
                i--;
                wordsCount = 0;
            }
        }
        if(oneRowWords.size()) {
            ans.push_back(createOneRowString(oneRowWords, leftSpace, 1));
        }
        return ans;
    }

    string createOneRowString(vector<string> oneRowWords, int leftSpace, int isTail) {
        string oneRowStr = "";
        if(isTail) {
            for(int i = 0; i < oneRowWords.size(); i++) {
                if(i == 0) {
                    oneRowStr += oneRowWords[i];
                }
                else {
                    oneRowStr += " " + oneRowWords[i];
                }
            }
            for(int i = 0; i < leftSpace - oneRowWords.size() + 1; i++) {
                oneRowStr += " ";
            }
        }
        else {
            if(oneRowWords.size() == 1) {
                string rightBlank(leftSpace, ' ');
                oneRowStr += oneRowWords[0] + rightBlank;
                return oneRowStr;
            }
            string rightBlank (leftSpace / (oneRowWords.size() - 1), ' ');
            int leftBlankCount = leftSpace % (oneRowWords.size() - 1);
            for(int i = 0; i < oneRowWords.size(); i++) {
                if(i == 0) {
                    oneRowStr += oneRowWords[i];
                }
                else {
                    if(i <= leftBlankCount)
                        oneRowStr += rightBlank + " " + oneRowWords[i];
                    else
                        oneRowStr += rightBlank + oneRowWords[i];
                }
            }
        }
        return oneRowStr;
    }
};