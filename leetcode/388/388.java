class Solution {
    public int lengthLongestPath(String input) {
        if(input.length() == 0) {
            return 0;
        }
        List<String> fileContents = new ArrayList<>();
        input = "\n" + input;
        int index = 0, maxFileLen = 0;
        while(index < input.length()) {
            int[] info = findTabCountAndFileStartIndex(input, index);
            int end = findNextNewlineIndex(input, info[1]);
            String content = input.substring(info[1], end);
            int insertIndex = info[0];
            if(insertIndex >= fileContents.size()) {
                fileContents.add(content);
            } else {
                fileContents.set(insertIndex, content);
            }
            if(content.contains(".")) {
                int fileLen = insertIndex;
                for(int i = 0;  i <= insertIndex; i++) {
                    fileLen += fileContents.get(i).length();
                }
                maxFileLen = Math.max(maxFileLen, fileLen);
            }
            index = end;
        }
        return maxFileLen;
    }

    // 找tab的个数和文件名称开始的位置
    public int[] findTabCountAndFileStartIndex(String input, int index) {
        int len = input.length(), tabCount = 0;
        while(index < len && (input.charAt(index) == '\t' || input.charAt(index) == '\n')) {
            if(input.charAt(index) == '\t') {
                tabCount++;
            }
            index++;
        }
        return new int[] {tabCount, index};
    }

    // 找下一个换行符开始的位置
    public int findNextNewlineIndex(String input, int index) {
        int len = input.length();
        while(index < len && input.charAt(index) != '\n') {
            index++;
        }
        return index;
    }
}