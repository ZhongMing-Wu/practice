class Solution {
    public String removeKdigits(String num, int k) {
        if(num.length() <= k) {
            return "0";
        }
        Deque<Integer> queue = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        int left = 0, right = 0;
        while(right < num.length()) {
            if(right - left == k) {
                pushEle(queue, num, right);
                int index = queue.pollFirst();
                sb.append(num.charAt(index));
                k = k - (index - left);
                left = index + 1;
                ++right;
                if(k == 0) {
                    break;
                }
                continue;
            }
            pushEle(queue, num, right);
            ++right;
        }

        if(k == 0) {
            for(int i = left; i < num.length(); ++i) {
                sb.append(num.charAt(i));
            } 
        }

        int i = 0;
        while(i < sb.length() && sb.charAt(i) == '0') {
            ++i;
        }
        if(i == sb.length()) {
            return "0";
        }
        sb.replace(0, i, "");
        return sb.toString();
    }

    private void pushEle(Deque queue, String num, int index) {
        while(!queue.isEmpty() && num.charAt((int)queue.peekLast()) > num.charAt(index)) {
            queue.pollLast();
        }
        queue.offerLast(index);
    }
}