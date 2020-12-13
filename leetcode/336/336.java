class Solution {
    Map<String, List<Integer>> toPrefixLength, toSuffixLength;
    Map<String, Integer> toLoc;
    public List<List<Integer>> palindromePairs(String[] words) {
        toPrefixLength = new HashMap<>(words.length);
        toSuffixLength = new HashMap<>(words.length);
        toLoc = new HashMap<>(words.length);
        List<Integer> prefixLenSet, suffixlenSet;
        for(int i = 0; i < words.length; i++) {
            prefixLenSet = new ArrayList<>();
            suffixlenSet = new ArrayList<>();
            toPrefixLength.put(words[i], prefixLenSet);
            toSuffixLength.put(words[i], suffixlenSet);
            setPrefixAndSuffixPalindromeLen(words[i]);
            toLoc.put(words[i], i);
        }

        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> oneAns;
        StringBuilder sb = new StringBuilder();
        String str;
        for(int i = 0; i < words.length; i++) {
            if(words[i].equals("")) {
                continue;
            }
            prefixLenSet = toPrefixLength.get(words[i]);
            for(int j = 0; j < prefixLenSet.size(); j++) {
                sb.replace(0, sb.length(), words[i].substring(prefixLenSet.get(j)));
                str = sb.reverse().toString();
                if(toLoc.containsKey(str)) {
                    oneAns = new ArrayList<>();
                    oneAns.add(toLoc.get(str));
                    oneAns.add(i);
                    ans.add(oneAns);
                }
            }

            suffixlenSet = toSuffixLength.get(words[i]);
            for(int j = 0; j < suffixlenSet.size(); j++) {
                sb.replace(0, sb.length(), words[i].substring(0, words[i].length() - suffixlenSet.get(j)));
                str = sb.reverse().toString();
                if(toLoc.containsKey(str)) {
                    oneAns = new ArrayList<>();
                    oneAns.add(i);
                    oneAns.add(toLoc.get(str));
                    ans.add(oneAns);
                }
            }
        }

        for(int i = 0; i < words.length; i++) {
            if(words[i].equals("")) {
                continue;
            }
            sb.replace(0, sb.length(), words[i]);
            str = sb.reverse().toString();
            if(toLoc.containsKey(str) && toLoc.get(str) != i) {
                oneAns = new ArrayList<>();
                oneAns.add(i);
                oneAns.add(toLoc.get(str));
                ans.add(oneAns);
            }
        }

        return ans;
    }

    private void setPrefixAndSuffixPalindromeLen(String word) {
        if(word.length() < 2) {
            toPrefixLength.get(word).add(word.length());
            toSuffixLength.get(word).add(word.length());
            return;
        }

        StringBuilder sb = new StringBuilder(word.length() * 2 + 1);
        sb.append('#');
        for(int i = 0; i < word.length(); i++) {
            sb.append(word.charAt(i));
            sb.append('#');
        }
        String str = sb.toString();
        int[] rightLength = new int[str.length()];
        int rightBoundary = -1, curCenter = 0;
        for(int i = 0; i < str.length(); i++) {
            if(i <= rightBoundary) {
                if(rightLength[2 * curCenter - i] >= rightBoundary - i) {
                    rightLength[i] = getRightLength(str, 2 * i - rightBoundary - 1, rightBoundary + 1);
                    curCenter = i;
                    rightBoundary = i + rightLength[i];
                } else {
                    rightLength[i] = rightLength[2 * curCenter - i];
                }
            } else {
                rightLength[i] = getRightLength(str, i - 1, i + 1);
                curCenter = i;
                rightBoundary = i + rightLength[i];
            }
        }

        for(int i = 1; i < rightLength.length - 1; i++) {
            if(i - rightLength[i] == 0) {
                toPrefixLength.get(word).add(rightLength[i]);
            }

            if(i + rightLength[i] == rightLength.length - 1) {
                toSuffixLength.get(word).add(rightLength[i]);
            }
        }
    }

    private int getRightLength(String str, int left, int right) {
        while(left >= 0 && right < str.length() && str.charAt(left) == str.charAt(right)) {
            left--;
            right++;
        }
        return (right - left) / 2 - 1;
    }
}