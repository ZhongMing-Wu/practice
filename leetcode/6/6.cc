class Solution {
public:
    string convert(string s, int numRows) {
        if(numRows==1||s.size()<=numRows)
            return s;
        vector<string> rows(numRows);
        int i=0,direction=1,rowNum=0;
        while(i<s.size()){
            rows[rowNum]+=s[i++];
            rowNum+=direction;
            if(rowNum==numRows){
                rowNum-=2;
                direction=-1;
            }
            else if(rowNum<0){
                rowNum=1;
                direction=1;
            }
        }
        string returnStr;
        for(int i=0;i<rows.size();i++)
            returnStr+=rows[i];
        return returnStr;
    }
};