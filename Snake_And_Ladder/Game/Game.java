package Snake_And_Ladder.Game ; 

public class Game {
    private final GameEngine engine ; 

    public Game(GameEngine engine){
        this.engine = engine ; 
    }

    public void start(){
        engine.play() ; 
    }
}