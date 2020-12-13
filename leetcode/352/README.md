### to 352 of leetcode website

[Data Stream as Disjoint Intervals](https://leetcode-cn.com/problems/data-stream-as-disjoint-intervals/)

**小结**
- 对于链表不能使用二分查找，可以通过一个数组(最好是平衡树)与链表建立对应关系，通过二分查找数组(或平衡树)快速定位待插入位置。(这种用法通常适用于对区间进行处理，数组(平衡树)中存储每个区间的特征值即可)