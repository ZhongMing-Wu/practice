type ProductOfNumbers struct {
    num []int
    zeroLoc int
    total int
}


func Constructor() ProductOfNumbers {
    return ProductOfNumbers {
        num: make([]int, 0, 40000),
        zeroLoc: -1,
        total: 0,
    }
}


func (this *ProductOfNumbers) Add(num int)  {
    var length = len(this.num)
    this.total++
    if num == 0 {
        this.zeroLoc = this.total - 1
        this.num = this.num[:0]
        return
    }
    if length == 0 {
        this.num = append(this.num, num)
    } else {
        this.num = append(this.num, this.num[length - 1] * num)
    }
}


func (this *ProductOfNumbers) GetProduct(k int) int {
    if this.total - k <= this.zeroLoc {
        return 0
    }
    var length = len(this.num)
    if k == length {
        return this.num[k - 1]
    }

    var res = this.num[length - 1] / this.num[length - 1 - k]
    return res
}


/**
 * Your ProductOfNumbers object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Add(num);
 * param_2 := obj.GetProduct(k);
 */