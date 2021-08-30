class Solution {
    private Map<Integer, Integer> occuredTime;
    public List<Integer> topKFrequent(int[] nums, int k) {
        if(nums.length == 0 || k == 0) {
            return new ArrayList<>();
        }
        List<Integer> heap = new ArrayList<>();
        occuredTime = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            if(occuredTime.containsKey(nums[i]) == false) {
                occuredTime.put(nums[i], 1);
            }
            else {
                occuredTime.put(nums[i], occuredTime.get(nums[i]) + 1);
            }
        }
        int i = 0;
        for(int key : occuredTime.keySet()) {
            if(i < k) {
                heap.add(key);
                i++;
            }
            else {
                for(int j = k / 2; j >= 0; j--) {
                    adjust(heap, j, k);
                }
                if(occuredTime.get(heap.get(0)) < occuredTime.get(key)) {
                    heap.set(0, key);
                    adjust(heap, 0, k);
                }
            }
        }
        heap.sort((Integer a, Integer b) -> occuredTime.get(b) - occuredTime.get(a));
        return heap;
    }

    void adjust(List<Integer> heap, int j, int k) {
        if(j * 2 + 1 >= k) {
            return;
        }
        int sonNode;
        for(int m = j; m * 2 + 1 < k; m = sonNode) {
            sonNode = m * 2 + 1;
            if(m * 2 + 2 < k) {
                if(occuredTime.get(heap.get(m * 2 + 1)) > occuredTime.get(heap.get(m * 2 + 2)) ) {
                    sonNode = m * 2 + 2;
                }
            }
            if(occuredTime.get(heap.get(m)) > occuredTime.get(heap.get(sonNode))) {
                int temp = heap.get(m);
                heap.set(m, heap.get(sonNode));
                heap.set(sonNode, temp);
            }
            else {
                break;
            }
        }
    }

}
