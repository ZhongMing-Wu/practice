class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> occurTime = new HashMap<>();
        for(int i = 0; i < words.length; ++i) {
            int time = occurTime.getOrDefault(words[i], 0);
            occurTime.put(words[i], time + 1);
        }
        PriorityQueue<String> pQueue = new PriorityQueue<>(k, (s1, s2)->{
            if(occurTime.get(s1) != occurTime.get(s2)) {
                return occurTime.get(s1) - occurTime.get(s2);
            }
            return s2.compareTo(s1);
        });
        
        for(String word : occurTime.keySet()) {
            if(pQueue.size() < k) {
                pQueue.offer(word);
                continue;
            }
            String curMinWord = pQueue.peek();
            if(occurTime.get(word) > occurTime.get(curMinWord) || (occurTime.get(word) == occurTime.get(curMinWord) && word.compareTo(curMinWord) < 0)) {
                pQueue.poll();
                pQueue.offer(word);
            }
        }
        List<String> list = new ArrayList<>();
        while(!pQueue.isEmpty()) {
            list.add(pQueue.poll());
        }

        Collections.reverse(list);
        return list;
    }
}