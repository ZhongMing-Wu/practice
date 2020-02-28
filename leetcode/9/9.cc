class Solution {
public:
    bool isPalindrome(int x) {
        if(x<0)
            return false;
        int num;
        string strX;
        while(x!=0){
            num=x%10;
            strX.push_back((num-'0'));
            x/=10;
        }
        bool flag=true;
        for(int i=0,j=strX.size()-1;i<j;i++,j--){
            if(strX[i]!=strX[j]){
                flag=false;
                break;
            }
        }
        return flag;
    }
};