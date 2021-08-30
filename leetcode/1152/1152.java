class Solution {
    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        int len = username.length;
        List<User> users = new ArrayList<>(len);
        for(int i = 0 ; i < len; i++) {
            users.add(new User(username[i], timestamp[i], website[i]));
        }

        users.sort((user1, user2) -> {
            return user1.timeStamp - user2.timeStamp;
        });

        HashMap<String, List<String>> toVisitedWebSite = new HashMap<>();
        for(User user : users) {
            List<String> visitedWebSite = null;
            if(!toVisitedWebSite.containsKey(user.username)) {
                toVisitedWebSite.put(user.username, new ArrayList<String>());
            }
            visitedWebSite = toVisitedWebSite.get(user.username);
            visitedWebSite.add(user.webSite);
        }
        HashMap<String, Integer> toScore = new HashMap<>();
        for(List<String> visitedWebSite : toVisitedWebSite.values()) {
            len = visitedWebSite.size();
            Set<String> patterns = new HashSet<>();
            for(int i = 0; i < len; i++) {
                for(int j = i + 1; j < len; j++) {
                    for(int k = j + 1; k < len; k++) {
                        String pattern = visitedWebSite.get(i) + " " + visitedWebSite.get(j) + " " +   visitedWebSite.get(k);
                        patterns.add(pattern);
                    }
                }
            }
            for(String pattern : patterns) {
                toScore.put(pattern, toScore.getOrDefault(pattern, 0) + 1);
            }
        }

        String ans = null;
        int curMaxScore = 0;
        for(Map.Entry<String, Integer> entry : toScore.entrySet()) {
            String key = entry.getKey();
            int score = entry.getValue();
            if(score > curMaxScore) {
                ans = key;
                curMaxScore = score;
                continue;
            }
            if(score == curMaxScore) {
                if(compareStrs(ans, key)) {
                    ans = key;
                }
            }
        }
        String[] strs = ans.split("\\s+");
        return Arrays.asList(strs);
    }

    public boolean compareStrs(String s1, String s2) {
        String[] strs1 = s1.split("\\s+");
        String[] strs2 = s2.split("\\s+");

        int len = strs1.length;
        boolean flag = false;
        for(int i = 0; i < len; i++) {
            int cmp = strs1[i].compareTo(strs2[i]);
            if(cmp != 0) {
                flag = cmp < 0 ? false : true;
                break;
            }
        }
        return flag;
    }
}

class User {
    public String username;
    public int timeStamp;
    public String webSite;

    public User(String username, int timeStamp, String webSite) {
        this.username = username;
        this.timeStamp = timeStamp;
        this.webSite = webSite;
    }
}