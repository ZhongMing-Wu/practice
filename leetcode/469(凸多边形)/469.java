class Solution {
    public boolean isConvex(List<List<Integer>> points) {
        long pre = 0, cur = 0;
        int dx1, dy1, dx2, dy2, n = points.size();
        for(int i = 0; i < n; ++i) {
            dx1 = points.get((i + 1) % n).get(0) - points.get(i).get(0);
            dy1 = points.get((i + 1) % n).get(1) - points.get(i).get(1);

            dx2 = points.get((i + 2) % n).get(0) - points.get((i + 1) % n).get(0);
            dy2 = points.get((i + 2) % n).get(1) - points.get((i + 1) % n).get(1);
            cur = (long)(dy1 * dx2 - dy2 * dx1);
            if(cur != 0) {
                if(cur * pre < 0) {
                    return false;
                }
                pre = cur;
            }
        }
        return true;
    }
}