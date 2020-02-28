class Solution {
    private Set<String> anses;
    private StringBuilder ans;
    private int maxLeftParenthesis = 0;
    public List<String> removeInvalidParentheses(String s) {
        anses = new HashSet<>();
        ans = new StringBuilder();
        if(s.length() == 0) {
            List<String> list = new ArrayList<>();
            list.add("");
            return new ArrayList<>(list);
        }
        int index = 0, leftParenthesis = 0, rightParenthesis = 0;
        findAll(s, index, leftParenthesis, rightParenthesis);
        return new ArrayList<String>(anses);
    }

    void findAll(String s, int index, int leftParenthesis, int rightParenthesis) {
        if(s.charAt(index) != '(' && s.charAt(index) != ')') {
            ans.append(s.charAt(index));
            tackle(s, index, leftParenthesis, rightParenthesis);
            ans.deleteCharAt(ans.length() - 1);
        }
        else if(s.charAt(index) == '(') {
            ans.append(s.charAt(index));
            leftParenthesis++;
            tackle(s, index, leftParenthesis, rightParenthesis);
            ans.deleteCharAt(ans.length() - 1);
            leftParenthesis--;
            tackle(s, index, leftParenthesis, rightParenthesis);
        }
        else {
            if(leftParenthesis > rightParenthesis) {
                ans.append(s.charAt(index));
                rightParenthesis++;
                tackle(s, index, leftParenthesis, rightParenthesis);
                ans.deleteCharAt(ans.length() - 1);
                rightParenthesis--;
            }
            tackle(s, index, leftParenthesis, rightParenthesis);
        }
        return;
    }

    void tackle(String s, int index, int leftParenthesis, int rightParenthesis) {
        if(index + 1 < s.length()) {
            findAll(s, index + 1, leftParenthesis, rightParenthesis);
        }
        else if(index + 1 == s.length()) {
            if(leftParenthesis == rightParenthesis && leftParenthesis >= maxLeftParenthesis) {
                maxLeftParenthesis = leftParenthesis;
                anses.add(ans.toString());
            }
        }
    }
}