package Snake_And_Ladder.Dices ; 

import java.util.Random ; 

public class StandardDice implements Dice {
    private final Random random = new Random() ; 

    @Override
    public int roll(){
        return random.nextInt(6) + 1 ; 
    }
}