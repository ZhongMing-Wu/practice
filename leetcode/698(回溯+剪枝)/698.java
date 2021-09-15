class Solution {
    boolean[] visited;
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0, len = nums.length;
        for(int i = 0;  i < len; i++) {
            sum += nums[i];
        }
        if(sum % k != 0) {
            return false;
        }
        int target = sum / k;
        Arrays.sort(nums);
        if(nums[len - 1] > target) {
            return false;
        } 
        visited = new boolean[len];
        return dfs(nums, 0, k, 0, target);
    }

    public boolean dfs(int[] nums, int count, int k, int curSum, int target) {
        if(count + 1 == k) {
            return true;
        }

        int len = nums.length, index = len - 1;
        while(index >= 0) {
            if(visited[index]) {
                index--;
                continue;
            }
            if(curSum + nums[index] == target) {
                visited[index] = true;
                boolean flag = dfs(nums, count + 1, k, 0, target);
                visited[index] = false;
                return flag;
            }
            if(curSum + nums[index] > target) {
                index--;
                continue;
            }
            visited[index] = true;
            boolean flag = dfs(nums, count, k, curSum + nums[index], target);
            if(flag) {
                return true;
            }
            visited[index] = false;
            index--;
            while(index > 0 && nums[index - 1] == nums[index]) {
                index--;
            }
            index--;
        }
        return false;
    }
}