class Solution {
    public int[] dailyTemperatures(int[] T) {
        int[] index = new int[101];
        int[] ans = new int[T.length];
        Stack<Integer> stack = new Stack<>();
        int topEle;
        for(int i = T.length - 1; i >= 0; i--) {
            if(stack.size() == 0) {
                stack.push(T[i]);
                ans[i] = 0;
            }
            else if(stack.peek() > T[i]) {
                topEle = stack.peek();
                stack.push(T[i]);
                ans[i] = index[topEle] - i;
            }
            else {
                while(stack.size() != 0 && stack.peek() <= T[i]) {
                    stack.pop();
                }
                if(stack.size() == 0) {
                    ans[i] = 0;
                }
                else {
                    topEle = stack.peek();
                    ans[i] = index[topEle] - i;
                }
                stack.push(T[i]);
            }
            index[T[i]] = i;
        }
        return ans;
    }
}