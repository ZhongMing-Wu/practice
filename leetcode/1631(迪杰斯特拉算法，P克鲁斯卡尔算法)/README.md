### to 1631 of leetcode website

[最小体力消耗路径](https://leetcode-cn.com/problems/path-with-minimum-effort/)

**小结**
- 需求是求某个点到某个点(或任一点)的最值问题，考虑迪杰斯特拉算法或克鲁斯卡尔算法(使用并查集)。
- 当需要记录的数据具有多个属性时，除了考虑新建一个 class，一种比较好的方案是用一个一维数组表示。例：
    - 比如该题中需要记录某个点所在的行，列和值，可以使用一个大小为3的一维数组表示。int[] node = new int[3]; node[0] 表示行，node[1] 表示列，node[2] 表示值。