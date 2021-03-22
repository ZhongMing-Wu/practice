class Solution {
    public String countOfAtoms(String formula) {
        Map<Integer, Integer> toRightBracketIndex = new HashMap<>();
        Deque<Integer> leftBracketIndex = new LinkedList<>();
        for(int i = 0; i < formula.length(); ++i) {
            if(formula.charAt(i) == '(') {
                leftBracketIndex.offerLast(i);
            } else if(formula.charAt(i) == ')'){
                toRightBracketIndex.put(leftBracketIndex.pollLast(), i);
            }
        }
    
        Map<String, Integer> occurTime = new HashMap<>();
        List<String> strs = new ArrayList<>();
        int curMultiple = 1, i = 0;
        while(i < formula.length()) {
            int numEnd, multiple;
            if(formula.charAt(i) >= 'A' && formula.charAt(i) <= 'Z') {
                int elementEnd = findElementEndIndex(formula, i + 1);
                String str = formula.substring(i, elementEnd);
                numEnd = findNumEndIndex(formula, elementEnd);
                if(elementEnd == numEnd) {
                    multiple = 1;
                } else {
                    multiple = Integer.valueOf(formula.substring(elementEnd, numEnd));
                }

                if(occurTime.containsKey(str)) {
                    occurTime.put(str, occurTime.get(str) + multiple * curMultiple);
                } else {
                    occurTime.put(str, multiple * curMultiple);
                    strs.add(str);
                }
                i = numEnd;
                continue;
            }
            if(formula.charAt(i) == '(') {
                int rightBracket = toRightBracketIndex.get(i);
                numEnd = findNumEndIndex(formula, rightBracket + 1);
                if(rightBracket + 1 != numEnd) {
                    multiple = Integer.valueOf(formula.substring(rightBracket + 1, numEnd));
                    curMultiple *= multiple;
                }
                ++i;
                continue;
            }
            if(formula.charAt(i) == ')') {
                numEnd = findNumEndIndex(formula, i + 1);
                if(i + 1 != numEnd) {
                    multiple = Integer.valueOf(formula.substring(i + 1, numEnd));
                    curMultiple /= multiple;
                }
                i = numEnd;
            }
        }
        Collections.sort(strs);
        StringBuilder sb = new StringBuilder();
        for(String s : strs) {
            int count = occurTime.get(s);
            if(count == 0) {
                continue;
            }
            if(count == 1) {
                sb.append(s);
            } else {
                sb.append(s + count);
            }
        }
        return sb.toString();
    }

    private int findElementEndIndex(String formula, int start) {
        while(start < formula.length() && formula.charAt(start) >= 'a' && formula.charAt(start) <= 'z') {
            ++start;
        }
        return start;
    }
    private int findNumEndIndex(String formula, int start) {
        while(start < formula.length() && formula.charAt(start) >= '0' && formula.charAt(start) <= '9') {
            ++start;
        }
        return start;
    }
}