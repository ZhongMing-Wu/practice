class Solution {
    public int minDays(int n) {
        HashMap<Integer, Integer> toTimes = new HashMap<>();
        return doMinDays(n, toTimes);
    }
    private int doMinDays(int n, HashMap<Integer, Integer> toTimes) {
        if(n <= 1) {
            return 1;
        }
        if(toTimes.containsKey(n)) {
            return toTimes.get(n);
        }
        int result = Math.min(n % 2 + 1 + doMinDays(n / 2, toTimes), n % 3 + 1 + doMinDays(n / 3, toTimes));
        toTimes.put(n, result);
        return result;
    }
}