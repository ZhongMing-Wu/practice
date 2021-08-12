### to 991 of leetcode website

[坏了的计算器](https://leetcode-cn.com/problems/broken-calculator/)

```java
// 关于利用 + - * / 计算x到y
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        String[] values = s.split(",");
        int x = Integer.valueOf(values[0]);
        int y = Integer.valueOf(values[1]);
        if(x == y) {
            System.out.println(0);
            return;
        }
        int maxOperateCount = Math.abs(x - y);
        Map<Integer, Integer> dp = new HashMap<>(401);
        for(int i = -200; i <= 200; i++) {
            dp.put(i, 1000000);
        }
        dp.put(y, 0);
        for(int i = 0; i < maxOperateCount; i++) {
            for(int j = 100; j >= -100; j--) {
                dp.put(j, Math.min(Math.min(dp.get(j), dp.get(j + 1) + 1), Math.min(dp.get(j - 1) + 1, dp.get(j * 2) + 1)));
            }
        }
        System.out.println(dp.get(x));
    }
}
```
**小结**
1. 这种题可以通过回溯+剪枝的方式处理
2. 如果一道题能够通过回溯+剪枝的方式处理，一般都能通过DP的方式处理
3. 991.java里面采用偏数学的方式处理，是因为只考虑乘法和减法，而且数字只有正数，更加简单