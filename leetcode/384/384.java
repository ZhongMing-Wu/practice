class Solution {

    int[] originArr, tackleArr;
    Random random; 
    public Solution(int[] nums) {
        originArr = nums;
        tackleArr = originArr.clone();
        random = new Random();
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        tackleArr = originArr.clone();
        return tackleArr;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        for(int i = 0; i < tackleArr.length; i++) {
            swapVal(i, getRange(i, tackleArr.length));
        }
        return tackleArr;
    }
    private void swapVal(int i, int j) {
        int temp = tackleArr[i];
        tackleArr[i] = tackleArr[j];
        tackleArr[j] = temp;
    }
    private int getRange(int left, int right) {
        return random.nextInt(right - left) + left;
    }
}