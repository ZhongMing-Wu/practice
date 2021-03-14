### to 469 of leetcode website

[凸多边形](https://leetcode-cn.com/problems/convex-polygon/)

**小结**
- 判断一个封闭图形是不是凸多边形，就是判断所有边的向量积是否同号(可以通过两两一次判断)。
- 向量积为 0，表示两条边平行，这时需要跳过，不能去改变记录的上一个向量积。