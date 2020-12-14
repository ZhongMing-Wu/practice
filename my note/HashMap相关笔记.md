##### HashMap 的 remove(Object key) 方法

**源码**

```java
public V remove(Object key) {
        Node<K,V> e;
        return (e = removeNode(hash(key), key, null, false, true)) == null ?
            null : e.value;
}
```

**说明**：删除指定 key 和对应的 value，**返回被删除的 key 的 value**

