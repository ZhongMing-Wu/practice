class Solution {
    public int findKthLargest(int[] nums, int k) {
        int left = 0, right = nums.length - 1, rankVal;
         while(true) {
             rankVal = quickSort(nums, left, right);
             if(rankVal == k) {
                 return nums[rankVal - 1];
             }
             else if(rankVal < k){
                left = rankVal;
             }
             else {
                 right = rankVal - 2;
             }
         }
    }

    private int quickSort(int[] nums, int left, int right) {
        int val = nums[left];
        while(left < right) {
            while(left < right && nums[right] <= val) {
                right--;
            }
            nums[left] = nums[right];
            while(left < right && nums[left] > val) {
                left++;
            }
            nums[right] = nums[left];
        }
        nums[left] = val;
        return left + 1;  //返回val是第几大的元素
    }
}