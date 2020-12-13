class Solution {
    public boolean isValidSerialization(String preorder) {
        if(0 == preorder.length()) {
            return true;
        }

        int slot = 1, start = 0, end;
        String nextStr;
        while(start < preorder.length()) {
            end = findNextComma(preorder, start);
            nextStr = preorder.substring(start, end);
            slot--;
            if(slot < 0) {
                return false;
            }
            if(!nextStr.equals("#")) {
                slot += 2;
            }
            start = end + 1;            
        }

        return slot == 0;
    }

    public int findNextComma(String preorder, int start) {
        int loc = start;
        while(loc != preorder.length() && preorder.charAt(loc) != ',') {
            loc++;
        }

        return loc;
    }
}