package Snake_And_Ladder.Player ; 

import java.util.UUID ; 

public class RandomIdGenerator implements PlayerIdGenerator {
    @Override
    public String generateId() {
        return "P-" + UUID.randomUUID().toString() ; 
    }
}