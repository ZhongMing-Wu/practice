/**
 * The rand7() API is already defined in the parent class SolBase.
 * public int rand7();
 * @return a random integer in the range 1 to 7
 */
class Solution extends SolBase {
    public int rand10() {
        int randomNum = 11;
        while(randomNum > 10) {
            randomNum = (rand7() - 1) * 7 + rand7();
        }
        return randomNum;
    }
}