class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if(numerator == 0) {
            return "0";
        }
        StringBuilder fraction = new StringBuilder();
        if(numerator < 0 ^ denominator < 0) {
            fraction.append("-");
        }
        long dividend = Math.abs(Long.valueOf(numerator));
        long divisor = Math.abs(Long.valueOf(denominator));

        fraction.append(String.valueOf(dividend / divisor));
        long remain = dividend % divisor;
        if(remain == 0) {
            return fraction.toString();
        }
        fraction.append(".");
        Map<Long, Integer> toLoc = new HashMap<>();
        while(remain != 0) {
            if(toLoc.containsKey(remain)) {
                fraction.insert(toLoc.get(remain), "(");
                fraction.append(")");
                return fraction.toString();
            }
            toLoc.put(remain, fraction.length());
            remain *= 10;
            fraction.append(String.valueOf(remain / divisor));
            remain %= divisor;
        }
        return fraction.toString();
    }
}