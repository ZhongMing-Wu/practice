class Solution {
    public int calculate(String s) {
        Map<Character, Integer> out = new HashMap<>();
        out.put('+', 1);
        out.put('-', 1);
        out.put('*', 3);
        out.put('/', 3);

        Map<Character, Integer> in = new HashMap<>();
        in.put('+', 2);
        in.put('-', 2);
        in.put('*', 4);
        in.put('/', 4);

        Deque<Integer> valStack = new ArrayDeque<>(s.length());
        Deque<Character> markStack = new ArrayDeque<>(s.length());

        StringBuilder num = new StringBuilder();
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                num.append(s.charAt(i));
                continue;
            }

            if(0 != num.length()) {
                valStack.addLast(Integer.parseInt(num.toString()));
                num.delete(0, num.length());
            }

            if(' ' == s.charAt(i)) {
                continue;
            }

            char c = s.charAt(i);
            if(markStack.size() == 0 || out.get(c) > in.get(markStack.getLast())) {
                markStack.addLast(c);
            } else {
                while(markStack.size() != 0 && out.get(c) < in.get(markStack.getLast())) {
                    char tempMark = markStack.pollLast();
                    int a = valStack.pollLast();
                    int b = valStack.pollLast();
                    valStack.addLast(calculate(b, a, tempMark));
                }
                markStack.addLast(c);
            }
        }

        if(0 != num.length()) {
            valStack.addLast(Integer.parseInt(num.toString()));
        }

        while(0 != markStack.size()) {
            char tempMark = markStack.pollLast();
            int a = valStack.pollLast();
            int b = valStack.pollLast();
            valStack.addLast(calculate(b, a, tempMark));
        }
        int ans = valStack.pollLast();
        return ans;
    }
    
    public int calculate(int a, int b, char mark) {
        int ans;
        switch(mark) {
            case '+':
            ans = a + b;
            break;
            case '-':
            ans = a - b;
            break;
            case '*':
            ans = a * b;
            break;
            default:
            ans = a / b;
        }
        return ans;
    }
}