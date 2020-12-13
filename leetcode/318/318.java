class Solution {
    public int maxProduct(String[] words) {
        Map<Integer, Integer> toMaxLength = new HashMap<>(words.length);
        List<Integer> marks = new ArrayList<>(words.length);
        for(int i = 0; i < words.length; i++) {
            int mark = 0;
            String temp = words[i];
            for(int j = 0; j < temp.length(); j++) {
                char c = temp.charAt(j);
                mark |= (1 << (c - 'a'));
            }

            if(!toMaxLength.containsKey(mark)) {
                toMaxLength.put(mark, temp.length());
                marks.add(mark);
                continue;
            }

            if(toMaxLength.get(mark) < temp.length()) {
                toMaxLength.put(mark, temp.length());
            }
        }

        int ans = 0;
        for(int i = 0; i < marks.size(); i++) {
            for(int j = i + 1; j < marks.size(); j++) {
                if((marks.get(i) & marks.get(j)) != 0) {
                    continue;
                }
                int len = toMaxLength.get(marks.get(i)) * toMaxLength.get(marks.get(j));
                ans = Math.max(ans, len);
            }
        }
        return ans;
    }
}