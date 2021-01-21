class Solution {
    public static final int TARGET = 24;
    public static final double EPSILON = 1e-6;
    public static final int ADD = 0, MULTIPLY = 1, SUBTRACT = 2, DIVIDE = 3;

    public boolean judgePoint24(int[] nums) {
        List<Double> list = new ArrayList<>(4);
        for(int num : nums) {
            list.add((double)num);
        }

        return solve(list);
    }

    private boolean solve(List<Double> list) {
        if(list.size() == 1) {
            return Math.abs(TARGET - list.get(0)) < EPSILON;
        }

        List<Double> nextList = new ArrayList<>(4);
        for(int i = 0; i < list.size(); ++i) {
            for(int j = 0; j < list.size(); ++j) {
                if(i != j) {
                    nextList.clear();
                    for(int k = 0; k < list.size(); ++k) {
                        if(k  != i && k != j) {
                            nextList.add(list.get(k));
                        }
                    }

                    for(int k = 0; k < 4; ++k) {
                        if(k < 2 && i > j) {
                            continue;
                        }

                        switch(k) {
                            case 0: nextList.add(list.get(i) + list.get(j)); break;
                            case 1: nextList.add(list.get(i) * list.get(j)); break;
                            case 2: nextList.add(list.get(i) - list.get(j)); break;
                            case 3: 
                                if(list.get(j) < EPSILON) {
                                    continue;
                                }
                                nextList.add(list.get(i) / list.get(j)); 
                                break;
                        }
                        if(solve(nextList)) {
                            return true;
                        }
                        nextList.remove(nextList.size() - 1);
                    }
                }
            }
        }

        return false;
    }
}