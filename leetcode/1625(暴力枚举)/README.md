### to 1625 of leetcode website

[执行操作后字典序最小的字符串](https://leetcode-cn.com/problems/lexicographically-smallest-string-after-applying-operations/)

**小结**
- 解题时，要考虑特殊情况的处理。比如该题每次改变的都是奇数位上的数字，看起来和偶数位上的数字无关，但是当轮转为奇数时，奇数位和偶数位的地位是相同的，都需要进行处理。
- 对于循环遍历问题，可以考虑是否可以使用某些值得最大公约数，以便一次遍历完成。