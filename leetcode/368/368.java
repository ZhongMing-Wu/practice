class Solution {
    List<int[]> anses;
    public List<Integer> largestDivisibleSubset(int[] nums) {
        if(nums.length == 0) {
            return new ArrayList<>();
        }
        anses = new ArrayList<>();
        Arrays.sort(nums);
        for(int i = 0; i < nums.length; i++) {
           findMaxLenAns(nums, i); 
        }

        int maxIndex = 0;
        for(int i = 0; i < nums.length; i++) {
            if(anses.get(maxIndex)[1] < anses.get(i)[1]) {
                maxIndex = i;
            }
        }

        List<Integer> ans = new ArrayList<>(anses.get(maxIndex)[1]);
        ans.add(nums[maxIndex]);
        int nextIndex = maxIndex;
        while(anses.get(nextIndex)[0] != -1) {
           nextIndex = anses.get(nextIndex)[0];
           ans.add(nums[nextIndex]); 
        }
        return ans;
    }

    private void findMaxLenAns(int[] nums, int index) {
        int[] oneAns = new int[2];
        oneAns[0] = -1;
        oneAns[1] = 0;
        anses.add(oneAns);
        for(int i = 0; i < index; i++) {
            if(nums[index] % nums[i] == 0 && anses.get(i)[1] + 1 > anses.get(index)[1]) {
                anses.get(index)[0] = i;
                anses.get(index)[1] = anses.get(i)[1];
            }
        }
        anses.get(index)[1] += 1;
    }
}