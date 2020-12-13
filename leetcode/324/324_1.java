class Solution {
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int[] temp = nums.clone();
        for (int i = 0; i < n / 2; i++) {
            nums[2 * i] = temp[(n - 1) / 2 - i];
            nums[2 * i + 1] = temp[n - 1 - i]; 
        }
        if (n % 2 == 1) nums[n - 1] = temp[0];
    }
}