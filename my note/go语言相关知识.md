##### go语言中清空 slice 的方法

```go
var nums = []int {1, 2, 3}
// 直接设置为 nil
nums = nil
// 通过 [:0] 的方式
nums = nums[:0]
```

##### go 语言中 slice 常见的方法

```go
var nums = make([]int, 5, 10)
// 追加元素
nums = append(nums, 5)
// 求长度
var length = len(nums)
```

##### for 循环中处理多个条件的注意点

```go
// 这种是正确的
for i, j := 0, 0; i < len(s); i, j = i + 1, j + 1{

}
// 这种是错误的，都需要使用平行赋值的方式
for i := 0, j := 0; i < len(s); i++, j++{

}
```

##### 创建二维切片 和 数组

```go
func main() {
	// 方法0
	row, column := 3, 4
	var answer [][]int
	for i := 0; i < row; i++ {
		inline := make([]int, column)
		answer = append(answer, inline)
	}
	fmt.Println(answer)

	// 方法1
	answer1 := make([][]int, row)
	for i := range answer1 {
		answer1[i] = make([]int, column)
	}
	fmt.Println(answer1)
    
    // 创建二维数组，长度要求必须是常数
    arrs := [10][10]int {}
}
```

##### 切片和数组的初始化方式

> ```go
> // go 语言中没有类似 Java 中的 fill
> // 使用循环的方式
> for j := 0; j < m; j++ {
>     dp[j] = xxx
> }
> // 声明的时候直接初始化
> nums := [][]int{
>     {0, 8, 7, 10,  9, 10, 0, 9,  6},
>     {8, 7, 10, 8,  7, 4,  9, 6,  10},
>     {8, 1, 1,  5,  1, 5,  5, 1,  2},
>     {9, 4, 10, 8,  8, 1,  9, 5,  0},
>     {4, 3, 6,  10, 9, 2,  4, 8,  10},
>     {7, 3, 2,  8,  3, 3,  5, 9,  8},
>     {1, 2, 6,  5,  6, 2,  0, 10, 0},
> }
> ```

##### 一个语法问题

> go 语言中没有  a == b?  1 : 2 这种方式

##### go语言中对数组进行排序

```go
type MyIntSlice [][]int

// 需要实现下面三种函数，由于 MyIntSlice 是切片，所以实现接口时，直接使用值传递的方式
func (m MyIntSlice) Len() int {
	return len(m)
}

func (m MyIntSlice) Less(i, j int) bool {
	return m[i][0] - m[i][1] < m[j][0] - m[j][1]
}

func (m MyIntSlice) Swap(i, j int) {
	m[i], m[j] = m[j], m[i]
}

func twoCitySchedCost(costs [][]int) int {
    var myCosts MyIntSlice = costs
	sort.Sort(myCosts)
	ans, length := 0, len(myCosts) / 2
	for i, j := 0, length; i < length; i, j = i + 1, j + 1 {
		ans += myCosts[i][0] + myCosts[j][1]
	}
	return ans
}
```

##### 使用 go 函数库中的排序方法

```go
sort.Strings(xxx) // 参数为 []string
sort.Ints(xxx) // 参数为 []ints，其他数据结构类似
// 对切片进行排序
sort.Slice(sorted, func(i, j int) bool {
    return len(sorted[i]) >= len(sorted[j])
})
```



##### go语言实现小堆

```go
package main

type IntHeap []int
 
// 由于 IntHeap 是切片，所以实现接口时，直接使用值传递的方式
func (h IntHeap) Len() int           { return len(h) }
func (h IntHeap) Less(i, j int) bool { return h[i] < h[j] }
func (h IntHeap) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }
// 下面两个函数需要改变切片的长度等变量，所以使用指针传递的方式
func (h *IntHeap) Push(x interface{}) {
    *h = append(*h, x.(int))
}
func (h *IntHeap) Pop() interface{} {
    old := *h
    n := len(old)
    x := old[n-1]
    *h = old[0 : n-1]
    return x
}
func main() {
    h := &IntHeap{2, 1, 5, 100, 3, 6, 4, 5}
    heap.Init(h)
    heap.Push(h, 3)
    fmt.Printf("minimum: %d\n", (*h)[0])
    for h.Len() > 0 {
        fmt.Printf("%d ", heap.Pop(h))
    }
}
```

##### go语言实现优先队列

```go
package main

type stu struct {
    name string
    age  int
}
type Stu []stu
 
// 由于结构体是非引用的数据结构，所以需要使用指针的方式传递
func (t *Stu) Len() int {
    return len(*t) 
}
 
func (t *Stu) Less(i, j int) bool {
    return (*t)[i].age < (*t)[j].age
}
func (t *Stu) Swap(i, j int) {
    (*t)[i], (*t)[j] = (*t)[j], (*t)[i]
}
func (t *Stu) Push(x interface{}) {
    *t = append(*t, x.(stu))
}
func (t *Stu) Pop() interface{} {
    n := len(*t)
    x := (*t)[n-1]
    *t = (*t)[:n-1]
    return x
}
func main() {
    student := &Stu{{"Amy", 21}, {"Dav", 15}, {"Spo", 22}, {"Reb", 11}}
    heap.Init(student)
    one := stu{"hund", 9}
    heap.Push(student, one)
    for student.Len() > 0 {
        fmt.Printf("%v\n", heap.Pop(student))
    }
 
}
```



