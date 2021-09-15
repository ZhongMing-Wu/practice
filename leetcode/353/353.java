class SnakeGame {
    int[][] food;
    HashSet<Integer> visited;
    LinkedList<int[]> snake;
    int width, height, foodIndex;
    HashMap<String, int[]> toDirection;
    /** Initialize your data structure here.
        @param width - screen width
        @param height - screen height 
        @param food - A list of food positions
        E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    public SnakeGame(int width, int height, int[][] food) {
        this.food = food;
        this.width = width;
        this.height = height;
        snake = new LinkedList<>();
        visited = new HashSet<>();
        snake.offerLast(new int[] {0, 0});
        visited.add(0);
        toDirection = new HashMap<>();
        toDirection.put("U", new int[] {-1, 0});
        toDirection.put("D", new int[] {1, 0});
        toDirection.put("L", new int[] {0, -1});
        toDirection.put("R", new int[] {0, 1});
        foodIndex = 0;
    }
    
    /** Moves the snake.
        @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down 
        @return The game's score after the move. Return -1 if game over. 
        Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        int[] moveDirection = toDirection.get(direction);
        int[] tail = snake.peekFirst();
        int[] head = snake.peekLast();
        int[] nextLoc = new int[] {head[0] + moveDirection[0], head[1] + moveDirection[1]};
        if(nextLoc[0] < 0 || nextLoc[0] >= height) {
            return -1;
        }
        if(nextLoc[1] < 0 || nextLoc[1] >= width) {
            return -1;
        }
        if(foodIndex < food.length && nextLoc[0] == food[foodIndex][0] && nextLoc[1] == food[foodIndex][1]) {
            foodIndex++;
            visited.add(nextLoc[0] * 100000 + nextLoc[1]);
            snake.offerLast(new int[] {nextLoc[0], nextLoc[1]});
            return foodIndex;
        }
        visited.remove(tail[0] * 100000 + tail[1]);
        snake.pollFirst();
        if(visited.contains(nextLoc[0] * 100000 + nextLoc[1])) {
            return -1;
        }
        visited.add(nextLoc[0] * 100000 + nextLoc[1]);
        snake.offerLast(new int[] {nextLoc[0], nextLoc[1]});
        return foodIndex;
    }
}

/**
 * Your SnakeGame object will be instantiated and called as such:
 * SnakeGame obj = new SnakeGame(width, height, food);
 * int param_1 = obj.move(direction);
 */