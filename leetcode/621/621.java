class Solution {
    public int leastInterval(char[] tasks, int n) {
        if(tasks.length == 0 || n == 0) {
            return tasks.length;
        }
        Map<Character, Integer> occuredTime = new HashMap<>();
        for(int i = 0; i < tasks.length; i++) {
            if(occuredTime.containsKey(tasks[i])) {
                occuredTime.put(tasks[i], occuredTime.get(tasks[i]) + 1);
            }
            else {
                occuredTime.put(tasks[i], 1);
            }
        }
        List<Integer> times = new ArrayList<>();
        for(char key : occuredTime.keySet()) {
            times.add(occuredTime.get(key));
        }
        int totalCount = 0, i;
        times.sort((a, b) -> b - a);
        while(times.get(0) != 1) {
            for(i = 0; i < n + 1 && i < times.size(); i++) {
                if(times.get(i) != 0) {
                    times.set(i, times.get(i) - 1);
                }
                else {
                    break;
                }
            }
            totalCount = totalCount + n + 1;
            times.sort((a, b) -> b - a);
        }
        for(i = 0; i < times.size(); i++) {
            if(times.get(i) == 0) {
                break;
            }
        }
        return totalCount + i;
    }
}