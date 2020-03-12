class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle.size() == 0) {
            return 0;
        }
        List<Integer> ans = new ArrayList<>();
        List<Integer> tempArray;
        ans.add(triangle.get(0).get(0));
        int pre = 0, tempVal;
        for(int i = 1; i < triangle.size(); i++) {
            tempArray = triangle.get(i);
            for(int j = 0; j < ans.size(); j++) {
                if(j == 0) {
                    pre = ans.get(j);
                    ans.set(j, ans.get(j) + tempArray.get(j));
                }
                else {
                    tempVal = tempArray.get(j) + Math.min(ans.get(j), pre);
                    pre = ans.get(j);
                    ans.set(j, tempVal);
                }
            }
            ans.add(pre + tempArray.get(ans.size()));
        }
        int minVal = Integer.MAX_VALUE;
        for(int val : ans) {
            minVal = val < minVal ? val : minVal;
        }
        return minVal;
    }
}