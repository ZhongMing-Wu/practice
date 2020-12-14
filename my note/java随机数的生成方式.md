##### 生成java随机数的两种方式

1. 使用 Math.random() 获取 [0, 1) 的随机数

```java
final double d = Math.random();
```

2. 通过 Random 类获取随机数

```java
Random random = new Random(); // 默认构造方法
Random random = new Random(1000); // 指定种子数字

int d = random.nextInt(100); // 获取 [0, 100) 之间的一个随机数
```

