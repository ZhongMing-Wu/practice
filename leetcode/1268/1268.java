class Solution {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Trie root = new Trie();
        Arrays.sort(products, (String s1, String s2) -> {
            return s1.compareTo(s2);
        });

        for(String product : products) {
            addProduct(root, product, 0);
        }
        List<List<String>> ans = new ArrayList<>();
        for(int i = 1; i <= searchWord.  length(); i++) {
            List<String> matchedProducts = findMatchedProduct(root, searchWord.substring(0, i));
            List<String> oneAns = new ArrayList<>();
            for(int j = 0; j < Math.min(3, matchedProducts.size()); j++) {
                oneAns.add(matchedProducts.get(j));
            }
            ans.add(oneAns);
        }
        return ans;
    }

    public void addProduct(Trie node, String product, int index) {
        if(index >= product.length()) {
            return;
        }
        if(node.children[product.charAt(index) - 'a'] == null) {
            node.children[product.charAt(index) - 'a'] = new Trie();
        }

        node.children[product.charAt(index) - 'a'].strs.add(product);
        addProduct(node.children[product.charAt(index) - 'a'], product, index + 1);
    }

    public List<String> findMatchedProduct(Trie node, String prefix) {
        if(node == null) {
            return new ArrayList<>();
        }
        for(int i = 0; i < prefix.length(); i++) {
            node = node.children[prefix.charAt(i) - 'a'];
            if(node == null) {
                return new ArrayList<>();
            }
        }
        return node.strs;
    }
}

class Trie {
    List<String> strs;
    Trie[] children;
    public Trie() {
        strs = new ArrayList<>();
        children = new Trie[26];
    }
}