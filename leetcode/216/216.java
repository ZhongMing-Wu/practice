class Solution {
    private List<List<Integer>> anses;
    public List<List<Integer>> combinationSum3(int k, int n) {
        anses = new ArrayList<List<Integer>>();
        if(n > 45 || k > 9) {
            return  anses;
        }
        List<Integer> ans = new ArrayList<>();
        DFS(k, n, 1, 0, ans);
        return anses;
    }
    void DFS(int k, int n, int start, int sum, List<Integer> ans) {
        if(n - sum < start) {
            return;
        }
        for(; start <= 9; start++) {
            ans.add(start);
            if(k == 1 && sum + start == n) {
                anses.add(new ArrayList<Integer>(ans));
                ans.remove(ans.size() - 1);
                return;
            }
            else if(k > 1 && sum + start < n) {
                DFS(k - 1, n, start + 1, sum + start, ans);
            }
            ans.remove(ans.size() - 1);
        }
    }
}