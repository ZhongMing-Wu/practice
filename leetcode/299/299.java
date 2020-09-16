class Solution {
    public String getHint(String secret, String guess) {
        int bullNum = 0, cowNum = 0;

        int[] occurTime = new int[10];
        for(int i = 0; i < secret.length(); i++) {
            char c1 = secret.charAt(i), c2 = guess.charAt(i);
            if(c1 == c2) {
                bullNum++;
                continue;
            }

            if(++occurTime[secret.charAt(i) - '0'] <= 0) {
                cowNum++;
            }

            if(--occurTime[guess.charAt(i) - '0'] >= 0) {
                cowNum++;
            }
        }

        return bullNum + "A" + cowNum + "B";
    }
}