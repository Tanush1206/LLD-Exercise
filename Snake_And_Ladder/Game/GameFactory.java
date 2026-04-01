package Snake_And_Ladder.Game;

import java.util.LinkedList;
import java.util.Queue;

import Snake_And_Ladder.Board.Board;
import Snake_And_Ladder.Dices.StandardDice;
import Snake_And_Ladder.Dices.Dice;
import Snake_And_Ladder.Player.Player;
import Snake_And_Ladder.Player.PlayerIdGenerator;
import Snake_And_Ladder.Player.RandomIdGenerator;

public class GameFactory {
    public static Game createGame(int n, int playerCount, String difficulty) {

        Board board = new Board(n);
        board.initializeJumps(n, difficulty);

        Dice dice = new StandardDice();

        PlayerIdGenerator idGen = new RandomIdGenerator();

        Queue<Player> players = new LinkedList<>();
        for (int i = 0; i < playerCount; i++) {
            players.add(new Player(idGen.generateId()));
        }

        GameEngine engine = new GameEngine(board, dice, players);
        return new Game(engine);
    }

}