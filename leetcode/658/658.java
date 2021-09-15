class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        Deque<Integer> closestSet = new LinkedList<>();
        int[] indexRange = findTargetIndexRange(arr, x);
        int left = indexRange[0], right = indexRange[1], n = arr.length;
        while(k > 0) {
            int leftGap = left >= 0 ? Math.abs(arr[left] - x) : Integer.MAX_VALUE;
            int rightGap = right < n ? Math.abs(arr[right] - x) : Integer.MAX_VALUE;
            if(leftGap <= rightGap) {
                closestSet.offerFirst(arr[left]);
                left--;
                k--;
            }
            if(leftGap > rightGap) {
                closestSet.offerLast(arr[right]);
                right++;
                k--;
            }
        }
        List<Integer> list = new ArrayList<>(closestSet);
        return list;
    }

    public int[] findTargetIndexRange(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while(left <= right) {
            int mid = (right - left) / 2 + left;
            if(arr[mid] == target) {
                return new int[] {mid, mid + 1};
            }
            if(arr[mid] < target) {
                left = mid + 1;
            }
            if(arr[mid] > target) {
                right = mid - 1;
            }
        }
        return new int[] {right, left};
    }
}