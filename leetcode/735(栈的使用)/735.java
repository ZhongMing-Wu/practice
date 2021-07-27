class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> stack = new LinkedList<>();
        boolean flag;
        for(int i = 0; i < asteroids.length; ++i) {
            flag = true;
            while(!stack.isEmpty() && asteroids[i] < 0 && stack.peekLast() > 0) {
                if(-asteroids[i] > stack.peekLast()) {
                    stack.pollLast();
                } else if(-asteroids[i] == stack.peekLast()) {
                    stack.pollLast();
                    flag = false;
                    break;
                } else {
                    flag = false;
                    break;
                }
            }
            if(flag) {
                stack.offerLast(asteroids[i]);
            }
        }
        int n = stack.size();
        int[] ans = new int[n];
        for(int i = n - 1; i >= 0; --i) {
            ans[i] = stack.pollLast();
        }
        return ans;
    }
}