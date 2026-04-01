package Snake_And_Ladder.Board;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import Snake_And_Ladder.Jumps.Jump;
import Snake_And_Ladder.Jumps.Ladder;
import Snake_And_Ladder.Jumps.Snake;

public class Board {
    private final int size;
    private final Map<Integer, Jump> jumps = new HashMap<>();

    public Board(int n) {
        this.size = n * n;
    }

    public int getSize() {
        return size;
    }

    public void initializeJumps(int n, String difficulty) {
        Random rand = new Random();

        int count = n; // n snakes & ladders

        while (count > 0) {
            int start = rand.nextInt(size - 1) + 1;
            int end = rand.nextInt(size - 1) + 1;

            if (start == end) continue;

            if (start > end) {
                jumps.put(start, new Snake(start, end));
                count--;
            } else {
                jumps.put(start, new Ladder(start, end));
                count--;
            }
        }
    }
    public int resolveJump(int pos) {
        if (jumps.containsKey(pos)) {
            return jumps.get(pos).getEnd();
        }
        return pos;
    }
}