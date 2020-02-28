#include <iostream>
#include <map>
#include <string>
using namespace std;
class Solution {
public:
    int lengthOfLongestSubstring(string s) {
        int maxLength=0,left=0;
        map<char,int> toLoc;
        int currentMax=0;
        for(int i=0;i<s.size();i++){
            if(!toLoc[s[i]]||toLoc[s[i]]-1<left){
                currentMax++;
                toLoc[s[i]]=i+1;
            }
            else{
                maxLength=max(maxLength,currentMax);
                currentMax=i+1-toLoc[s[i]];
                left=toLoc[s[i]];
                toLoc[s[i]]=i+1;
            }
        }
         maxLength=max(maxLength,currentMax);
        return maxLength;
    }
};
int main(int argc,char *argv[]){
    Solution _solution;
    string s="abba";
    int maxLength=_solution.lengthOfLongestSubstring(s);
    cout<<maxLength<<endl;
    return 0;
}