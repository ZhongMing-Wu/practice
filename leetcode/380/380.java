class RandomizedSet {

    Map<Integer, Integer> toIndex;
    List<Integer> list;
    Random random;
    /** Initialize your data structure here. */
    public RandomizedSet() {
        toIndex = new HashMap<>();
        list = new ArrayList<>();
        random = new Random();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(toIndex.containsKey(val)) {
            return false;
        }
        list.add(val);
        toIndex.put(val, list.size() - 1);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(!toIndex.containsKey(val)) {
            return false;
        }
        int index = toIndex.get(val);
        list.set(index, list.get(list.size() - 1));
        list.remove(list.size() - 1);
        toIndex.remove(val);
        if(index == list.size()) {
            return true;
        }
        toIndex.put(list.get(index), index);
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        return list.get(random.nextInt(list.size()));
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */