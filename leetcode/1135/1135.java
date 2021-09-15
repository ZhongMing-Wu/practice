class Solution {
    public int minimumCost(int n, int[][] connections) {
        int[] father = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            father[i] = i;
        }
        Arrays.sort(connections, (int[] connection1, int[] connection2) -> {
            return connection1[2] - connection2[2];
        });

        int cost = 0, connCount = 0;
        for(int[] connection : connections) {
            int city1 = connection[0], city2 = connection[1];
            if(!checkUnion(father, city1, city2)) {
                unionTwoCities(father, city1, city2);
                cost += connection[2];
                connCount++;
            }
        }
        if(connCount == n - 1) {
            return cost;
        }
        return -1;
    }

    public int findFather(int[] father, int num) {
        int p = num;
        while(father[p] != p) {
            p = father[p];
        }

        int c = num;
        while(father[c] != p) {
            int temp = father[c];
            father[c] = p;
            c = temp;
        }
        return p;
    }

    public void unionTwoCities(int[] father, int num1, int num2) {
        int p1 = findFather(father, num1);
        int p2 = findFather(father, num2);
        father[p2] = p1;
    }

    public boolean checkUnion(int[] father, int num1, int num2) {
        int p1 = findFather(father, num1);
        int p2 = findFather(father, num2);
        return p1 == p2;
    }
}