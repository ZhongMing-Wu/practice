class Solution {
public:
    int reverse(int x) {
        int returnVal=0,lastNum;
        while(x!=0){
            lastNum=x%10;
            if(returnVal>INT_MAX/10||(returnVal==INT_MAX/10&&lastNum>7)) return 0;
            if(returnVal<INT_MIN/10||(returnVal==INT_MIN/10&&lastNum<-8)) return 0;
            returnVal=returnVal*10+x%10;
            x/=10;
        }
        return returnVal;
    }
};