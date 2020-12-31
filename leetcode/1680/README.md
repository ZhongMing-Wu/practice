### to 1680 of leetcode website

[Concatenation of Consecutive Binary Numbers](https://leetcode-cn.com/problems/concatenation-of-consecutive-binary-numbers/)

**小结**
- 对于如何判断某个数的二进制有多少位，一种方式是直接计算，一种是在递增处理的环境下，判断当前数的二进制位数，可以通过上一个数的二进制位数求得。通过 (i & (i - 1)) == 0 来判断。如果等于0，说明位数增加了一位，在上一个数的二进制位数上 +1 即可。
- 通过字符串计算其对应的整数值的一种方法，例：
```
// 可以很好的处理动态的末尾增加数值的情况
String s = 1231231;
int ans = 0;
for(int i = 0; i < s.length(); i++) {
    char c = s.charAt(i);
    ans = (ans << 1) + (c - '0')
}
