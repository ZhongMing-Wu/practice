class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;    
        int[] father = new int[n + 1];
        for(int i = 1; i <= n; ++i) {
            father[i] = i;
        }

        int[] ans = null;
        for(int i = 0; i < n; ++i) {
            int num1 = doFindFather(father, edges[i][0]);
            int num2 = doFindFather(father, edges[i][1]);
            if(num1 == num2) {
                ans = edges[i];
            }
            doUnionNode(num1, num2, father);
        }
        return ans;
    }

    private int doFindFather(int[] father, int num) {
        int f = num;
        while(father[f] != f) {
            f = father[f];
        }
        while(father[num] != f) {
            int temp = father[num];
            father[num] = f;
            num = temp;
        }
        return f;
    }

    private void doUnionNode(int num1, int num2, int[] father) {
        father[num2] = num1;
    }
}