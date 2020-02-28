class Solution {
public:
    string getPermutation(int n, int k) {
        visited.resize(n + 1);
        string oneAns = "";
        int kthStr = 0;
        DFS(kthStr, k, n, oneAns);
        return ans;
    }
    void DFS(int& kthStr, int k, int n, string oneAns) {
        for(int i = 1; i <= n; i++) {
            if(flag) {
                return;
            }
            if(!visited[i]) {
                oneAns.push_back('0' + i);
                visited[i] = 1;
                if(oneAns.size() == n) {
                    kthStr++;
                    if(kthStr == k) {
                        ans = oneAns;
                        flag = 1;
                    }
                    visited[i] = 0;
                    oneAns.pop_back();
                    return;
                }
                else {
                    DFS(kthStr, k, n, oneAns);
                    visited[i] = 0;
                    oneAns.pop_back();
                }
            }
        }
    }
private:
    vector<int> visited;
    string ans;
    int flag = 0;
};   