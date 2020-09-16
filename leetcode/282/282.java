class Solution {
    private StringBuilder sb;
    private List<String> ans;

    public List<String> addOperators(String num, int target) {
        if(num.length() == 0) {
            return new ArrayList<String>();
        }
        sb = new StringBuilder();
        ans = new ArrayList<String>();
        calculate(num, target, 0, 0, 0, 0, '+');
        return ans;
    }

    private void calculate(String num, int target, int index, long preMultiVal, long combineNum, long curVal, char mark) {
        if(index == num.length()) {
            if(curVal == target) {
                ans.add(sb.toString());
            }
            return;
        }

        char tempVal = num.charAt(index);
        if(0 == index) {
            sb.append(tempVal);
            calculate(num, target, index + 1, tempVal - '0', tempVal - '0', tempVal - '0', '+');
            return;
        }

        // 处理 '+'
        sb.append('+').append(tempVal);
        calculate(num, target, index + 1, tempVal - '0', tempVal - '0', curVal + tempVal - '0', '+');

        // 处理 '-'
        sb.delete(sb.length() - 2, sb.length());
        sb.append('-').append(tempVal);
        calculate(num, target, index + 1, tempVal - '0', tempVal - '0', curVal - (tempVal - '0'), '-');

        // 处理 '*'
        long temp = 0;
        if(mark == '+') {
            temp = curVal - preMultiVal + preMultiVal * (tempVal - '0');
        } 
        if(mark == '-') {
            temp = curVal + preMultiVal - preMultiVal * (tempVal - '0');
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append('*').append(tempVal);
        calculate(num, target, index + 1, preMultiVal * (tempVal - '0'), tempVal - '0', temp, mark);

        // 处理当前数字与前面的数结合
        sb.delete(sb.length() - 2, sb.length());
        if(0 == combineNum) {
            return;
        }
        if(mark == '+') {
            temp = curVal - preMultiVal + preMultiVal / combineNum * (combineNum * 10 + tempVal - '0');
        }
        if(mark == '-') {
            temp = curVal + preMultiVal - preMultiVal / combineNum * (combineNum * 10 + tempVal - '0');
        }
        sb.append(tempVal);
        calculate(num, target, index + 1, preMultiVal / combineNum * (combineNum * 10 + tempVal - '0'), combineNum * 10 + tempVal - '0', temp, mark);

        // 将 sb 恢复
        sb.delete(sb.length() - 1, sb.length());    
        return;    
    }
}