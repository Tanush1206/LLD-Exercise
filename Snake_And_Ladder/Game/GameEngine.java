package Snake_And_Ladder.Game ; 

import java.util* ;
import Snake_And_Ladder.Board.Board ;
import Snake_And_Ladder.Dices.Dice ;
import Snake_And_Ladder.Player.Player ;

public class GameEngine {
    private final Board ; 
    private final Dice dice ; 
    private final Queue<Player> players ; 

    public GameEngine(Board board , Dice dice , Queue<Player> players){
        this.board = board ; 
        this.dice = dice ; 
        this.players = players ;
    }

    public void play(){
        while(players.size() > 1){
            Player curr = players.poll() ; 

            int roll = dice.roll() ; 
            int nextPos = curr.getPostion() + roll() ; 

            if(nextPos > board.getSize()){
                player.offer(curr) ; 
                continue ; 
            }

            nextPos = board.resolveJump(nextPos) ; 
            curr.setPostion(nextPos) ;

            System.out.println(current.getId() + " rolled " + roll + " -> " + nextPos) ; 

            if(nextPos == board.getSize()){
                System.out.println(curr.getId() + " wins! ") ; 
            } else {
                player.offer(curr) ; 
            }
        }
    }
}