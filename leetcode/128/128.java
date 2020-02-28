class Solution {
    public int longestconsecutive(int[] nums) {
        Set<Integer> num_set = new HashSet<Integer>();

        for(int num : nums) {
            num_set.add(num);
        }

        int longestSreak = 0;

        for(int num : nums) {
            if(!num_set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                while(num_set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                longestSreak = Math.max(currentStreak, longestSreak);
            }
        }

        return longestSreak;
    }
}