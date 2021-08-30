class Solution {
    public boolean isRobotBounded(String instructions) {
        int direction = 0;
        int[][] action = new int[][] {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
        
        int[] curLoc = new int[] {0, 0};
        for(int i = 0; i < instructions.length(); i++) {
            char c = instructions.charAt(i);
            switch(c) {
                case 'L':
                    direction = (direction + 1) % 4;
                    break;
                case 'R':
                    direction = (direction + 3) % 4;
                    break;
                default:
                    curLoc[0] += action[direction][0];
                    curLoc[1] += action[direction][1];
            }
        }
        if(direction != 0) {
            return true;
        }
        if(curLoc[0] == 0 && curLoc[1] == 0) {
            return true;
        }
        return false;
    }
}