class NumArray {

    private int[] tree;
    private int len;
    public NumArray(int[] nums) {
        len = nums.length;
        tree = new int[len * 2];
        buildTree(nums);
    }
    
    public void update(int i, int val) {
        int loc = i + len, gap = val - tree[loc];
        while(loc > 0) {
            tree[loc] += gap;
            loc = loc / 2;
        }
    }
    
    public int sumRange(int i, int j) {
        int left = i + len;
        int right = j + len;
        int sum = 0;
        while(left <= right) {
            if(left % 2 == 1) {
                sum += tree[left];
                left++;
            }
            if(right % 2 == 0) {
                sum += tree[right];
                right--;
            }

            left /= 2;
            right /= 2;
        }
        return sum;
    }

    private void buildTree(int[] nums) {
        for(int i = len, j = 0; j < len; i++, j++) {
            tree[i] = nums[j];
        }
        for(int i = len - 1; i >= 0; i--) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1]; 
        }
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */