class Solution {
public:
    int myAtoi(string str) {
        int flag1=1,flag2=0;
        int i=0,returnVal=0,num;
        if(str.size()==0)
            return 0;
        while(i<str.size()){
            if(str[i]==' '){
                if(flag2)
                    break;
                i++;
            }
            else if(str[i]=='-'){
                if(flag2)
                    break;
                i++;
                flag1=0;
                flag2=1;
            }
            else if(str[i]=='+'){
                if(flag2)
                    break;
                i++;
                flag2=1;
            }
            else if(str[i]>='0'&&str[i]<='9'){
                flag2=1;
                num=str[i]-'0';
                if(flag1){
                    if(returnVal>INT_MAX/10||(returnVal==INT_MAX/10&&num>7)) return INT_MAX;
                    else
                        returnVal=returnVal*10+num;
                }
                else{
                    if(returnVal<INT_MIN/10||(returnVal==INT_MIN/10&&num>8)) return INT_MIN;
                    else
                        returnVal=returnVal*10-num;
                }
                i++;
            }
            else
                break;
        }
        return returnVal;
    }
};