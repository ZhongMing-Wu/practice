class Solution {
    private int index;
    public boolean isValidSerialization(String preorder) {
        if(preorder.charAt(0) == '#' && 1 == preorder.length()) {
            return true;
        }

        if(0 == preorder.length()) {
            return true;
        }

        index = 0;
        List<String> nodes = new ArrayList<>(preorder.length());
        int start = 0, end;
        while(start < preorder.length()) {
            end = findNextComma(preorder, start);
            nodes.add(preorder.substring(start, end));
            start = end + 1;
        }

        boolean ans = preOrder(nodes);
        if(index == nodes.size() - 1) {
            return ans;
        }

        return false;
    }

    public boolean preOrder(List<String> nodes) {
        if(index == nodes.size()) {
            return false;
        }

        if(nodes.get(index).equals("#")) {
            return true;
        }
        index++;
        boolean left = preOrder(nodes);
        if(!left) {
            return false;
        }
        index++;
        boolean right = preOrder(nodes);

        return right;
    }

    public int findNextComma(String preorder, int start) {
        int loc = start;
        while(loc != preorder.length() && preorder.charAt(loc) != ',') {
            loc++;
        }

        return loc;
    }
}