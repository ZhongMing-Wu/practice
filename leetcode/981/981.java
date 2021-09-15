class TimeMap {

    /** Initialize your data structure here. */
    HashMap<String, List<Node>> toNodes;
    public TimeMap() {
        toNodes = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        if(!toNodes.containsKey(key)) {
            toNodes.put(key, new ArrayList<>());
        }
        List<Node> list = toNodes.get(key);
        Node node = new Node(value, timestamp);
        list.add(node);
    }
    
    public String get(String key, int timestamp) {
        if(!toNodes.containsKey(key)) {
            return "";
        }
        List<Node> list = toNodes.get(key);
        int right = list.size() - 1, left = 0;
        if(timestamp >= list.get(right).timeStamp) {
            return list.get(right).value;
        }
        if(timestamp < list.get(left).timeStamp) {
            return "";
        }

        while(left <= right) {
            int mid = (right - left ) / 2 + left;
            Node node = list.get(mid);
            if(node.timeStamp == timestamp) {
                return node.value;
            }
            if(node.timeStamp > timestamp) {
                right = mid - 1;
            }
            if(node.timeStamp < timestamp) {
                left = mid + 1;
            }
        }

        Node node = list.get(right);
        return node.value;
    }
}

class Node {
    String value;
    int timeStamp;

    public Node(String value, int timeStamp) {
        this.value = value;
        this.timeStamp = timeStamp;
    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */