class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int alias = 0;
        HashMap<Integer, String> toRealName = new HashMap<>();
        HashMap<String, Integer> toAlias = new HashMap<>();
        HashMap<Integer, Integer> toFather = new HashMap<>();
        for(List<String> account : accounts) {
            int result = check(account, toAlias, toFather);
            if(result != -1) {
                for(int i = 1; i < account.size(); i++) {
                    toAlias.put(account.get(i), result);
                }
                continue;
            }
            String name = account.get(0);
            toRealName.put(alias++, name);
            for(int i = 1; i < account.size(); i++) {
                toAlias.put(account.get(i), alias - 1);
            }
        }
        List<List<String>> tempAns = new ArrayList<>(alias);
        for(int i = 0; i < alias; i++) {
            tempAns.add(new ArrayList<>());
        }

        for(Map.Entry<String, Integer> entry : toAlias.entrySet()) {
            String email = entry.getKey();
            int index = entry.getValue();
            index = findFather(toFather, index);
            List<String> account = tempAns.get(index);
            account.add(email);
        }

        List<List<String>> ans = new ArrayList<>();
        for(int i = 0; i < tempAns.size(); i++) {
            List<String> emails = tempAns.get(i);
            if(emails.size() > 0) {
                emails.sort(Comparator.naturalOrder());
                String name = toRealName.get(i);
                List<String> account = new ArrayList<>();
                account.add(name);
                account.addAll(emails);
                ans.add(account);
            }
        }
        return ans;
    }

    // 判断该组邮件账号是否属于某一个已遍历用户
    public int check(List<String> account, HashMap<String, Integer> toAlias, HashMap<Integer, Integer> toFather) {
        List<Integer> father = new ArrayList<>();
        for(int i = 1; i < account.size(); i++) {
            String oneAccount = account.get(i);
            if(toAlias.containsKey(oneAccount)) {
                father.add(toAlias.get(oneAccount));
            }
        }
        if(father.size() == 1) {
            return father.get(0);
        }
        if(father.size() > 1) {
            int f = findFather(toFather, father.get(0));
            for(int i = 1; i < father.size(); i++) {
                toFather.put(findFather(toFather, father.get(i)), f);
            }
            return f;
        }
        return -1;
    }

    public int findFather(HashMap<Integer, Integer> toFather, int num) {
        int f = num;
        while(toFather.containsKey(f) && toFather.get(f) != f) {
            f = toFather.get(f);
        }

        int c = num;
        while(toFather.containsKey(c) && toFather.get(c) != f) {
            int temp = toFather.get(c);
            toFather.put(c, f);
            c = temp;
        }
        return f;
    }
}