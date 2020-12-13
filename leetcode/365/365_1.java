class Solution {
    Map<Integer, Integer> exist;
    Queue<Integer> queue;
    public boolean canMeasureWater(int x, int y, int z) {
        if(x + y == z || z == 0) {
            return true;
        }

        if(Math.max(x, y) * 2 < z) {
            return false;
        }
        if(x == 0 || y == 0) {
            return x == z || y == z;
        }

        exist = new HashMap<>(16);
        queue = new LinkedList<>();

        addWater(0, x, y);
        addWater(0, y, x);

        while(!queue.isEmpty()) {
            boolean ans = validate(x, y, z);
            if(ans) {
                return true;
            }
        }
        return false;
    }

    public void addWater(int origin, int x, int y) {
        int temp = origin;
        while(temp <= x) {
            if(!exist.containsKey(temp)) {
                queue.offer(temp);
                exist.put(temp, 1);
                temp += y;
            } else {
                temp += y;
            }
        }
        if(!exist.containsKey(temp - x)) {
            queue.offer(temp - x);
            exist.put(temp - x, 1);
        }
    }

    public boolean validate(int x, int y, int z) {
        int num = queue.poll();
        if(num == z || num + x == z || num + y == z) {
            return true;
        }
        addWater(num, x, y);
        addWater(num, y, x);
        return false;
    }
}