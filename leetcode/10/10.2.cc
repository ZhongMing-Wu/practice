class Solution {
public:
    bool isMatch(string s, string p) {
        int ns=s.size()+1,np=p.size()+1;
        vector<vector<int>> dp;
        dp.resize(ns);
        for(int i=0;i<ns;i++)
            dp[i].resize(np);
        dp[0][0]=1;
        for(int i=0;i<ns;i++)
            for(int j=1;j<np;j++){
                if(i>0 && (p[j-1]=='.'||p[j-1]==s[i-1]))
                    dp[i][j]=dp[i-1][j-1];
                if(p[j-1]=='*'){
                    if(i==0||(s[i-1]!=p[j-2]&&p[j-2]!='.'))
                        dp[i][j]=dp[i][j-2];
                    else
                        dp[i][j]=dp[i][j-1]||dp[i][j-2]||dp[i-1][j];
                }
            }
        return dp[ns-1][np-1]==1?true:false;
    }
};