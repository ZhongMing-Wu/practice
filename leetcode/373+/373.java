class Solution {
    private class Node {
        public int first;
        public int second;

        public Node(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> anses = new ArrayList<>(k);
        if(k == 0 || nums1.length == 0 || nums2.length == 0) {
            return anses;
        }

        int len1 = nums1.length;
        Node[] nodeHeap = new Node[len1 + 1];
        for(int i = 0; i < len1; i++) {
            Node node = new Node(i, 0);
            nodeHeap[i + 1] = node;
        }

        buildHeap(nodeHeap, nums1, nums2);
        List<Integer> ans;
        int curLen = nums1.length;
        for(int i = 0; i < k && i < nums1.length * nums2.length; i++) {
            ans = new ArrayList<>(2);
            ans.add(nums1[nodeHeap[1].first]);
            ans.add(nums2[nodeHeap[1].second]);
            anses.add(ans);
            nodeHeap[1].second++;
            if(nodeHeap[1].second == nums2.length) {
                nodeHeap[1] = nodeHeap[curLen];
                curLen--;
            }
            if(curLen != 0) {
                adjustHeap(nodeHeap, nums1, nums2, 1, curLen);
            }
        }
        return anses;
    }

    public void buildHeap(Node[] nodeHeap, int[] nums1, int[] nums2) {
        for(int i = (nodeHeap.length - 1) / 2; i >= 1; i--) {
            adjustHeap(nodeHeap, nums1, nums2, i, nodeHeap.length - 1);
        }
    }

    public void adjustHeap(Node[] nodeHeap, int[] nums1, int[] nums2, int loc, int curLen) {
        Node temp = nodeHeap[loc];
        int parentVal = nums1[nodeHeap[loc].first] + nums2[nodeHeap[loc].second];
        int minimumSon, minimumSonVal, leftVal, rightVal;
        while(loc * 2 <= curLen) {
            rightVal = Integer.MAX_VALUE;
            minimumSon = loc * 2; 
            leftVal = nums1[nodeHeap[loc * 2].first] + nums2[nodeHeap[loc * 2].second];

            if(loc * 2 + 1 <= curLen) {
                rightVal = nums1[nodeHeap[loc * 2 + 1].first] + nums2[nodeHeap[loc * 2 + 1].second];
            }

            if(leftVal > rightVal) {
                minimumSon++;
                minimumSonVal = rightVal;
            } else {
                minimumSonVal = leftVal;
            }

            if(parentVal > minimumSonVal) {
                nodeHeap[loc] = nodeHeap[minimumSon];
                loc = minimumSon;
                continue;
            }
            break;
        }
        nodeHeap[loc] = temp;
    }
}