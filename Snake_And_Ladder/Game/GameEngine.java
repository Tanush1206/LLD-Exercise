package Snake_And_Ladder.Game;

import java.util.Queue;

import Snake_And_Ladder.Board.Board;
import Snake_And_Ladder.Dices.Dice;
import Snake_And_Ladder.Player.Player;

public class GameEngine {
    private final Board board;
    private final Dice dice;
    private final Queue<Player> players;

    public GameEngine(Board board, Dice dice, Queue<Player> players) {
        this.board = board;
        this.dice = dice;
        this.players = players;
    }

    public void play() {
        while (players.size() > 1) {
            Player current = players.poll();

            int roll = dice.roll();
            int nextPos = current.getPosition() + roll;

            if (nextPos > board.getSize()) {
                players.offer(current);
                continue;
            }

            nextPos = board.resolveJump(nextPos);
            current.setPosition(nextPos);

            System.out.println(current.getId() + " rolled " + roll + " -> " + nextPos);

            if (nextPos == board.getSize()) {
                System.out.println(current.getId() + " WON!");
            } else {
                players.offer(current);
            }
        }
    }
}