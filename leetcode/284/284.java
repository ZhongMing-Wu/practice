// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html

class PeekingIterator implements Iterator<Integer> {
    private Integer curVal;
    private Iterator<Integer> itr;
	public PeekingIterator(Iterator<Integer> iterator) {
	    // initialize any member here.
	    itr = iterator;
        curVal = null;
	}
	
    // Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
        if(curVal != null) {
            return curVal;
        }
        curVal = itr.next();
        return curVal;
	}
	
	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
	    if(curVal != null) {
            int temp = curVal;
            curVal = null;
            return temp;
        }
        return itr.next();
	}
	
	@Override
	public boolean hasNext() {
	    return curVal != null || itr.hasNext();
	}
}