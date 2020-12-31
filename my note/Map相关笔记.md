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



##### HashMap 的 getOrDefault(Object key, V defaultValue)

**源码**

```java
default V getOrDefault(Object key, V defaultValue) {
        V v;
        return (((v = get(key)) != null) || containsKey(key))
            ? v
            : defaultValue;
    }
```

**说明**:如果map中Key不存在，则返回默认值。



##### TreeMap 的 lowerKey（K key）

>此方法返回的最大 key 严格小于key；如果没有这样的 key ，则返回null。

