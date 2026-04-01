package Snake_And_Ladder;

import java.util.Scanner;

import Snake_And_Ladder.Game.Game;
import Snake_And_Ladder.Game.GameFactory;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // board size
        int players = sc.nextInt();
        String difficulty = sc.next();

        Game game = GameFactory.createGame(n, players, difficulty);
        game.start();
    }
}