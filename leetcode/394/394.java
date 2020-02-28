class Solution {
    public String decodeString(String s) {
        if(s.length() == 0) {
            return "";
        }
        return tacklePartStr(1, s);
    }

    String tacklePartStr(int time, String partStr) {
        StringBuilder ans = new StringBuilder();
        int pre = 0, cur = 0, occuredTime;
        int[] bracketsLoc = new int[2];
        do {
            cur = findNumStartLocation(partStr, cur);
            if(cur != pre) {
                ans.append(partStr.substring(pre, cur));
            }
            if(cur != partStr.length()) {
                occuredTime = findNextNum(partStr, cur);
                bracketsLoc = findBrackets(partStr, cur);
                String partAns = tacklePartStr(occuredTime, partStr.substring(bracketsLoc[0] + 1, bracketsLoc[1]));
                for(int i = 0; i < occuredTime; i++) {
                    ans.append(partAns);
                }
                pre = cur = bracketsLoc[1] + 1;
            }
        } while(cur < partStr.length());
        return ans.toString();
    }

    int findNextNum(String partStr, int start) {
        StringBuilder num = new StringBuilder();
        for(int i = start; i < partStr.length(); i++) {
            if(partStr.charAt(i) >= '0' && partStr.charAt(i) <= '9') {
                num.append(partStr.charAt(i));
            }
            else if(num.length() != 0) {
                break;
            }
        }
        return Integer.parseInt(num.toString());
    }

    int findNumStartLocation(String partStr, int start) {
        for(int i = start; i < partStr.length(); i++) {
            if(partStr.charAt(i) >= '1' && partStr.charAt(i) <= '9') {
                return i;
            }
        }
        return partStr.length();
    }

    int[] findBrackets(String partStr, int start) {
        int leftBrackets = 0;
        int[] bracketsLoc = new int[2];
        for(int i = start; i < partStr.length(); i++) {
            if(partStr.charAt(i) == '[') {
                leftBrackets++;
                if(bracketsLoc[0] == 0) {
                    bracketsLoc[0] = i;
                }
            }
            else if(partStr.charAt(i) == ']') {
                leftBrackets--;
                if(leftBrackets == 0) {
                    bracketsLoc[1] = i;
                    break;
                }
            }
        }
        return bracketsLoc; 
    }
}