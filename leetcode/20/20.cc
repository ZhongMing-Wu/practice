class Solution {
public:
    bool isValid(string s){ 
        bool flag=true;
        for(int i=0;i<s.size();i++){
            if(s[i]=='{'||s[i]=='('||s[i]=='[')
                _stack.push(s[i]);
            else{
                if(_stack.empty()){
                    flag=false;
                    break;
                }else if(s[i]==')'){
                    if(_stack.top()=='(')
                        _stack.pop();
                    else{
                        flag=false;
                        break;
                    }
                }else if(s[i]==']'){
                    if(_stack.top()=='[')
                        _stack.pop();
                    else{
                        flag=false;
                        break;
                    }
                }else{
                    if(_stack.top()=='{')
                        _stack.pop();
                    else{
                        flag=false;
                        break;
                    }
                }
            }
        }
        if(flag==true)
            if(!_stack.empty())
                flag=false;
        return flag;
    }
private:
    stack<char> _stack;
};