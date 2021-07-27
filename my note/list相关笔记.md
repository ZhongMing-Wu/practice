##### List 转 Array

```java
public static void main(String[] args) throws Exception {
    List<String> list = new ArrayList<String>();
    list.add("A");
    list.add("B");
    list.add("C");

    // 使用泛型，无需显式类型转换
    String[] array = list.toArray(new String[list.size()]);
    System.out.println(array[0]);
}
```

##### Array 转 List

```java
String[] arrays = {"a", "b", "c"};
List<String> list = Arrays.asList(arrays);
```

> 将数组转换成 List 后，不能对 List 增删，只能查改，Arrays.asList(strArray)返回值是java.util.Arrays类中一个私有静态内部类java.util.Arrays.ArrayList，它并非java.util.ArrayList类。java.util.Arrays.ArrayList类具有 set()，get()，contains()等方法，但是不具有添加add()或删除remove()方法,所以调用add()方法会报错。

##### list中的remove操作

```java
List<Integer> list = new ArrayList<>();
list.remove(int index);
list.remove(Object o);
```