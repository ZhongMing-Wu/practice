class Solution {
    public int calculate(String s) {
        List<String> suffixExp = new ArrayList<>();
        Deque<Character> stack = new LinkedList<>();
        HashMap<Character, Integer> inStack = new HashMap<>();
        HashMap<Character, Integer> outStack = new HashMap<>();

        initPriority(inStack, outStack);
        for(int i = 0; i < s.length();) {
            char c = s.charAt(i);
            if(Character.isDigit(c)) {
                int end = findNumEnd(s, i);
                suffixExp.add(s.substring(i, end));
                i = end;
                continue;
            }
            if(c == ')') {
                while(stack.peekLast() != '(') {
                    suffixExp.add(String.valueOf(stack.pollLast()));
                }
                stack.pollLast();
                i++;
                continue;
            }
            while(!stack.isEmpty() && inStack.get(stack.peekLast()) > outStack.get(c)) {
                suffixExp.add(String.valueOf(stack.pollLast()));
            }
            stack.offerLast(c);
            i++;
        }
        while(!stack.isEmpty()) {
            suffixExp.add(String.valueOf(stack.pollLast()));
        }
    
        return calculateSuffixExpression(suffixExp);
    }

    public void initPriority(HashMap<Character, Integer> inStack, HashMap<Character, Integer> outStack) {
        inStack.put('+', 2);
        inStack.put('-', 2);
        inStack.put('*', 4);
        inStack.put('/', 4);
        inStack.put('(', 0);

        outStack.put('+', 1);
        outStack.put('-', 1);
        outStack.put('*', 3);
        outStack.put('/', 3);
        outStack.put('(', 5);
    }

    public int calculateSuffixExpression(List<String> suffixExp) {
        Deque<String> stack = new LinkedList<>();
        if(suffixExp.size() == 1) {
            return Integer.valueOf(suffixExp.get(0));
        }
        for(int i = 0; i < suffixExp.size(); i++) {
            if(Character.isDigit(suffixExp.get(i).charAt(0))) {
                stack.offerLast(suffixExp.get(i));
                continue;
            }
            int num2 = Integer.valueOf(stack.pollLast());
            int num1 = Integer.valueOf(stack.pollLast());
            switch(suffixExp.get(i)) {
                case "+":
                    stack.offerLast(String.valueOf(num1 + num2));
                    break;
                case "-":
                    stack.offerLast(String.valueOf(num1 - num2));
                    break;
                case "*":
                    stack.offerLast(String.valueOf(num1 * num2));
                    break;
                case "/":
                    stack.offerLast(String.valueOf(num1 / num2));
                    break;
            }
        }
        return Integer.valueOf(stack.pollLast());
    }

    public int findNumEnd(String s, int start) {
        int len = s.length(), end = start;
        while(end < len) {
            if(!Character.isDigit(s.charAt(end))) {
                break;
            }
            end++;
        }
        return end;
    }
}