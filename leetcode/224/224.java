class Solution {
    public int calculate(String s) {
        // 设定各个符号的优先级
        Map<Character, Integer> out = new HashMap<>();
        out.put('+', 1);
        out.put('-', 1);
        out.put('(', 3);
        Map<Character, Integer> in = new HashMap<>();
        in.put('+', 2);
        in.put('-', 2);
        in.put('(', 0);

        // 存储等式中所有的数值
        List<Integer> valPostFixExp = new ArrayList<>(s.length());
        // 存储等式中所有的运算符
        List<Character> markPostFixExp = new ArrayList<>(s.length());
        // 存储每一个数值后面对应的运算符个数
        int[] markCount = new int[s.length()];
        // 存储数字使用s.length()
        StringBuilder num = new StringBuilder();
        // 模拟栈
        Deque<Character> stack1 = new ArrayDeque<>();

        // 将 s 转成后缀表达式 
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                num.append(s.charAt(i));
                continue;
            }
            if(0 != num.length()) {
                valPostFixExp.add(Integer.parseInt(num.toString()));
                num.delete(0, num.length());
            }
            
            if(' ' == s.charAt(i)) {
                continue;
            }

            if(')' == s.charAt(i)) {
                while('(' != stack1.getLast()) {
                    char temp = stack1.pollLast();
                    markPostFixExp.add(temp);
                    markCount[valPostFixExp.size() - 1]++;
                }
                stack1.pollLast();
                continue;
            }

            if(0 == stack1.size()) {
                stack1.addLast(s.charAt(i));
                continue;
            }

            char c = s.charAt(i);
            if(in.get(stack1.getLast()) < out.get(c)) {
                stack1.addLast(c);
            } else {
                while(0 != stack1.size()) {
                    char temp = stack1.pollLast();
                    markPostFixExp.add(temp);
                    markCount[valPostFixExp.size() - 1]++;
                    if(0 == stack1.size() || in.get(stack1.getLast()) < out.get(c)) {
                        stack1.addLast(c);
                        break;
                    }
                }
            }
        }

        if(num.length() != 0) {
            valPostFixExp.add(Integer.parseInt(num.toString()));
        }

        while(0 != stack1.size()) {
            char temp = stack1.pollLast();
            markPostFixExp.add(temp);
            markCount[valPostFixExp.size() - 1]++;
        }

        // 计算表达式的值
        Deque<Integer> stack2 = new ArrayDeque<>();
        int ans;
        int i = 0, j = 0;  // i 为valPostFixExp当前位置, j 为markPostFixExp当前位置
        for(; i < valPostFixExp.size(); i++) {
            stack2.addLast(valPostFixExp.get(i));
            if(0 != markCount[i]) {
                while(markCount[i] != 0) {
                    if(markPostFixExp.get(j) == '-') {
                        int temp1 = stack2.pollLast();
                        int temp2 = stack2.pollLast();
                        stack2.addLast(temp2 - temp1);
                        markCount[i]--;
                        j++;
                    } else if(markPostFixExp.get(j) == '+') {
                        int temp1 = stack2.pollLast();
                        int temp2 = stack2.pollLast();
                        stack2.addLast(temp2 + temp1);
                        markCount[i]--;
                        j++;
                    }
                }
            }
        }

        ans = stack2.pollLast();
        return ans;
    }
}