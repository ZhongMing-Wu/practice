### to 1665 of leetcode website

[Minimum Initial Energy to Finish Tasks](https://leetcode-cn.com/problems/minimum-initial-energy-to-finish-tasks/)

**小结**
- 一般要求求解最值问题，一种思路是考虑贪心，贪心的核心在于考虑影响结果的所有条件，并找到这些影响因素他们影响的标准。比如 1665 这道题，影响的条件是消耗值和能够开始的门限值，他们影响结果的标准是前一个任务门限值 - 消耗值的差值应该大于后一个任务的差值。
- 当要解决一个整体问题的时候，可以先从局部下手。一种情况是局部的性质和整体的性质是一致的。