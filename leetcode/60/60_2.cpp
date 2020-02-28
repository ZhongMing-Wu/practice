class Solution {
public:
    string getPermutation(int n, int k) {
        visited.resize(n + 1);
        int i = 1;  //表示当前正查找ans第i个位置上的元素
        string ans = "";
        int total;   //用于记录同一个字符开头的字符串的总数有多少个
        int loc, item;
        while(i < n) {
            total = calculateTotal(n - i);
            loc = k / total + ((k % total == 0) ? 0 : 1);
            item = findTheIthItem(loc, n);
            visited[item] = 1;
            ans.push_back('0' + item);
            i++;
            k = k - total * (loc - 1);
        }
        for(int i = 1; i <= 10; i++) {
            if(!visited[i]) {
                ans.push_back('0' + i);
                break;
            }
        }
        return ans;
    }

    int calculateTotal(int n) {    //用于计算n的阶乘
        int ans = 1;
        while(n > 1) {
            ans *= n;
            n--;
        }
        return ans;
    }

    int findTheIthItem(int loc, int n) {   //查找写入到ans第I个位置的元素
        for(int i = 1; i <= n; i++) {
            if(!visited[i]) {
                loc--;
                if(loc == 0) {
                    return i;
                }
            }
        }
        return 0;
    }
private:
    vector<int> visited;
};