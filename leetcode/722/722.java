class Solution {
    public List<String> removeComments(String[] source) {
        boolean block =false;
        List<String> ans = new ArrayList<>();
        StringBuilder sb = null;
        for(String s : source) {
            if(!block) {
                sb = new StringBuilder();
            }
            int n = s.length();
            for(int i = 0; i < n;) {
                if(i + 1 < n && !block && s.charAt(i) == '/' && s.charAt(i + 1) == '*') {
                    block = true;
                    i++;
                } else if(i + 1 < n && block && s.charAt(i) == '*' && s.charAt(i + 1) == '/') {
                    block = false;
                    i++;
                } else if(i + 1 < n && !block && s.charAt(i) == '/' && s.charAt(i + 1) == '/') {
                    break;
                } else if(!block) {
                    sb.append(s.charAt(i));
                }
                i++;
            }
            if(!block && sb.length() > 0) {
                ans.add(sb.toString());
            }
        }
        return ans;
    }
}