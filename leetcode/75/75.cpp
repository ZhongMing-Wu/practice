class Solution {
public:
    void sortColors(vector<int>& nums) {
        int red = 0, blue = nums.size() - 1;
        int current = 0;
        while(current <= blue) {
            switch(nums[current]) {
                case 0: 
                    swapTwoElement(nums[red], nums[current]); 
                    current++;
                    red++;
                    break;
                case 1:
                    current++;
                    break;
                case 2:
                    swapTwoElement(nums[blue], nums[current]);
                    blue--;
            }
        }
    }

    void swapTwoElement(int &a, int &b) {
        int temp = a;
        a= b;
        b= temp;
    }
};