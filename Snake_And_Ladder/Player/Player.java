package Snake_And_Ladder.Player ;

public class Player {
    private final String id ; 
    private int position ; 

    public Player(String id){
        this.id = id ; 
        this.position = 0 ; 
    }

    public String getId(){
        return id ; 
    }

    public int getPostion(){
        return position ; 
    }

    public void setPostion(int position){
        this.position = position ; 
    }
}