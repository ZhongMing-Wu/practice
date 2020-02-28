class Solution {
    private Map<String, Double> vals;
    private Map<String, String> father;
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        vals = new HashMap<>();
        father = new HashMap<>();
        String dividend, divisor;
        String dividendFather, divisorFather;
        for(int i = 0; i < equations.size(); i++) {
            dividend = equations.get(i).get(0);
            divisor = equations.get(i).get(1);
            vals.put(dividend + "." + divisor, values[i]);
            if(father.containsKey(dividend) == false) {
                father.put(dividend, dividend);
            }
            if(father.containsKey(divisor) == false) {
                dividendFather = findFather(dividend);
                father.put(divisor, dividendFather);
                if(dividendFather.equals(dividend) == false) {
                    vals.put(dividendFather + "." + divisor, vals.get(dividendFather + "." + dividend) * vals.get(dividend + "." + divisor));
                }
            }
            else {
                divisorFather = findFather(divisor);
                dividendFather = findFather(dividend);
                father.put(divisorFather, dividendFather);
                vals.put(dividendFather + "." + divisorFather, claculate(dividendFather, dividend) * vals.get(dividend + "." + divisor) / claculate(divisorFather, divisor));
            }
        }

        double[] ans = new double[queries.size()];
        for(int i = 0; i < queries.size(); i++) {
            dividend = queries.get(i).get(0);
            divisor = queries.get(i).get(1);
            dividendFather = findFather(dividend);
            divisorFather = findFather(divisor);
            if(dividendFather == null || divisorFather == null || divisorFather.equals(dividendFather) == false) {
                ans[i] = -1.0;
            }
            else if(dividend.equals(divisor) == true){
                ans[i] = 1.0;
            }
            else {
                if(dividendFather.equals(dividend) == true) {
                    ans[i] = vals.get(dividend + "." + divisor);
                }
                else if(divisorFather.equals(divisor) == true) {
                    ans[i] = 1.0 / vals.get(divisor + "." + dividend);
                }
                else {
                    ans[i] = vals.get(divisorFather + "." + divisor) / vals.get(dividendFather + "." + dividend);
                }
            }
        }
        return ans;
    }

    double claculate(String str1, String str2) {
        if(str1.equals(str2) == true) {
            return 1.0;
        }
        double result = 1.0;
        while(father.get(str2).equals(str1) == false) {
            result *= vals.get(father.get(str2) + "." + str2);
            str2 = father.get(str2);
        }
        result *= vals.get(str1 + "." + str2);
        return result;
    }
    
    String findFather(String str) {
        if(father.containsKey(str) == false) {
            return null;
        }
        List<String> list = new ArrayList<>();
        while(father.get(str).equals(str) == false) {
            list.add(str);            
            str = father.get(str);
        }
        list.add(str);
        double result = 1.0;
        for(int i = list.size() - 2; i >= 0; i--) {
            father.put(list.get(i), str);
            vals.put(str + "." + list.get(i), result * vals.get(list.get(i + 1) + "." + list.get(i)));
            result = result * vals.get(list.get(i + 1) + "." + list.get(i));
        }
        return str;
    }
}