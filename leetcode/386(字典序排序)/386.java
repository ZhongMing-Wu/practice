class Solution {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> ans = new ArrayList<>(n);
        for(int i = 1; i < 10; ++i) {
            dfs(n, i, ans);
        }
        return ans;
    }

    public void dfs(int n, int curVal, List<Integer> ans) {
        if(curVal > n) {
            return;
        }

        ans.add(curVal);
        for(int i = 0; i < 10; ++i) {
            dfs(n, curVal * 10 + i, ans);
        }
    }
}