class Solution {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        if(0 == k) {
            return new int[0];
        }

        if(0 == nums1.length) {
            return maxNumberOfNums(nums2, k);
        }

        if(0 == nums2.length) {
            return maxNumberOfNums(nums1, k);
        }

        int[] part1, part2;

        List<int[]> anses = new ArrayList<>(k + 1);
        for(int i = 0; i <= k; i++) {
            part1 = maxNumberOfNums(nums1, i);
            part2 = maxNumberOfNums(nums2, k - i);

            if(part1.length != i) {
                continue;
            }

            if(part2.length != (k - i)) {
                continue;
            }
            anses.add(mergeNums(part1, part2));
        }

        int[] ans = anses.get(0);
        for(int i = 1; i < anses.size(); i++) {
            ans = compareNums(ans, anses.get(i));
        }
        return ans;
    }

    private int[] maxNumberOfNums(int[] nums, int k) {
        if(0 == k || k > nums.length) {
            return new int[0];
        }

        Deque<Integer> stack = new LinkedList<>();

        int curCount = 1, len = nums.length;
        stack.push(nums[0]);
        
        for(int i = 1; i < len; i++) {
            while(!stack.isEmpty() && stack.peek() < nums[i] && k - curCount + 1 <= len - i) {
                stack.pop();
                curCount--;
            }

            if(curCount == k) {
                continue;
            }

            stack.push(nums[i]);
            curCount++;
        }


        int[] ans = new int[k];
        for(int i = k - 1; i >= 0; i--) {
            ans[i] = stack.pop();
        }

        return ans;
    }

    public int[] mergeNums(int[] part1, int[] part2) {
        int len1 = part1.length;
        int len2 = part2.length;

        int[] ans = new int[len1 + len2];

        int index = 0, i = 0, j = 0;
        for(; i < len1 && j < len2;) {
            if(part1[i] > part2[j]) {
                ans[index++] = part1[i++];
            } else if(part1[i] < part2[j]) {
                ans[index++] = part2[j++];
            } else {
                if(chooseFirst(i + 1, j + 1, part1, part2)) {
                    ans[index++] = part1[i++];
                } else {
                    ans[index++] = part2[j++];
                }
            }
        }

        while(i < len1) {
            ans[index++] = part1[i++];
        }

        while(j < len2) {
            ans[index++] = part2[j++];
        }

        return ans;
    }

    private int[] compareNums(int[] ans1, int[] ans2) {
        for(int i = 0; i < ans1.length; i++) {
            if(ans1[i] == ans2[i]) {
                continue;
            }

            return ans1[i] > ans2[i] ? ans1 : ans2;
        }

        return ans1;
    }

    private boolean chooseFirst(int i, int j, int[] part1, int[] part2) {
        int len1 = part1.length, len2 = part2.length;

        for(;i < len1 && j < len2; i++, j++) {
            if(part1[i] == part2[j]) {
                continue;
            }

            return part1[i] > part2[j] ? true : false;
        }

        return i == len1 ? false : true;
    }
}