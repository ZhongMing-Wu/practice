class Solution {
    public int[] prisonAfterNDays(int[] cells, int n) {
        Map<Integer, Integer> toTime = new HashMap<>();
        Map<Integer, Integer> toCells = new HashMap<>();

        int time = 0, value = 0;
        while(true) {
            if(time == n) {
                return cells;
            }
            value = calculate(cells);
            if(toTime.containsKey(value)) {
                break;
            } else {
                toTime.put(value, time);
                toCells.put(time, value);
            }
            time++;
            transferToValue(cells);
        }
        int lastTime, cycleTime, targetTime, targetValue;
        lastTime = toTime.get(value);
        cycleTime = time - lastTime;
        targetTime = (n - lastTime + 1) % cycleTime;
        targetValue = toCells.get(lastTime + (targetTime + cycleTime - 1) % cycleTime);
        return transferToCells(targetValue);
    }

    public int calculate(int[] cells) {
        int value = 0;
        for(int cell : cells) {
            value = value * 2 + cell;
        }
        return value;
    }

    public void transferToValue(int[] cells) {
        int lastCell = cells[0], nextCell = 0, len = cells.length;
        for(int i = 1; i < len - 1; i++) {
            nextCell = cells[i];
            if(lastCell == cells[i + 1]) {
                cells[i] = 1;
            } else {
                cells[i] = 0;
            }
            lastCell = nextCell;
            nextCell = cells[i + 1];
        }
        cells[0] = 0;
        cells[len - 1] = 0;
    }

    public int[] transferToCells(int value) {
        int[] cells = new int[8];
        int index = 7;
        while(value > 0) {
            cells[index--] = value % 2;
            value /= 2;
        }
        return cells;
    }
}