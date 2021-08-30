/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

public class Solution extends Relation {
    public int findCelebrity(int n) {
        int famous = 0;
        for(int i = 1; i < n; i++) {
            if(knows(famous, i)) {
                famous = i;
            }
        }

        for(int i = 0; i < n; i++) {
            if(famous == i) {
                continue;
            }
            if(knows(famous, i) || !knows(i, famous)) {
                return -1;
            }
        }
        return famous;
    }
}