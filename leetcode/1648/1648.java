class Solution {
    public int maxProfit(int[] inventory, int orders) {
        long left = 0, right = 0;
        for(int i = 0; i < inventory.length; ++i) {
            right = Math.max(right, inventory[i]);
        }
        int threshHold = 0;
        while(left <= right) {
            long middle = (left + right) / 2, down = 0, up = 0; 
            for(int i = 0; i < inventory.length; ++i) {
                if(inventory[i] >= middle) {
                    down += inventory[i] - middle;
                    up += inventory[i] - middle + 1;
                }
            }
            if(down <= orders && up > orders) {
                threshHold = (int)middle;
                break;
            }
            if(up <= orders) {
                right = middle - 1;
                continue;
            }
            left = middle + 1;
        }
        long ans = 0;
        for(int i = 0; i < inventory.length; ++i) {
            if(threshHold < inventory[i]) {
                ans += ((long)(inventory[i] + threshHold + 1) * (long)(inventory[i] - threshHold)) / 2;
                ans %= 1000000007;
                orders -= inventory[i] - threshHold;
            }
        }
        
        while(orders != 0) {
            ans += threshHold;
            --orders;
            ans %= 1000000007;
        }
        return (int)ans;
    }
}