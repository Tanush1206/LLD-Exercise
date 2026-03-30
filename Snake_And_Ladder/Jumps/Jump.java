package Snake_And_Ladder.Jumps ; 

public abstract class Jump {
    protected int start ; 
    protected int end ; 

    public Jump(int start , int end){
        this.start = start ; 
        this.end = end ; 
    }

    public int getEnd(){
        return end ; 
    }
}