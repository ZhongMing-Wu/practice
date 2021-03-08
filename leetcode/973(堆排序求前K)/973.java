class Solution {
    public int[][] kClosest(int[][] points, int K) {
        int m = points.length;
        if(K > m) {
            return points;
        }
        int[][] heap = new int[K][2];
        for(int i = 0; i < K; ++i) {
            heap[i][0] = points[i][0];
            heap[i][1] = points[i][1];
        }

        for(int i = K / 2; i >= 0; --i) {
            adjustHeap(heap, i, K);
        }

        for(int i = K; i < m; ++i) {
            int[] point = points[i];
            if(compareDis(heap[0], point)) {
                heap[0] = point;
                adjustHeap(heap, 0, K);
            }
        }

        return heap;
    }

    private void adjustHeap(int[][] heap, int index, int len) {
        int[] point = heap[index];
        for(int i = index * 2; i < len; i = i * 2) {
            if(i + 1 < len && compareDis(heap[i + 1], heap[i])) {
                i = i + 1;
            }
            if(compareDis(heap[i], point)) {
                heap[index] = heap[i];
                index = i;
            } else {
                break;
            }
        }
        heap[index] = point;
    }

    private boolean compareDis(int[] point1, int[] point2) {
        int dis1 = point1[0] * point1[0] + point1[1] * point1[1];
        int dis2 = point2[0] * point2[0] + point2[1] * point2[1];
        if(dis1 > dis2) {
            return true;
        }
        return false;
    }
}