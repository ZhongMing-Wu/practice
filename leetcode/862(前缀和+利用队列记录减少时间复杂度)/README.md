### to 862 of leetcode website

[和至少为 K 的最短子数组](https://leetcode-cn.com/problems/shortest-subarray-with-sum-at-least-k/)

**小结**
- 针对求连续子串(子数组)问题，一种思路是考虑前缀和，因为任何连续子串都可以由两个前缀和相减得到。
- 一种减少时间复杂度的有效方式是通过一个单调栈或者单调队列记录数据信息，至于按照哪种方式进行单调处理，需要根据题意去探究（这里是解决这种问题的痛点）。