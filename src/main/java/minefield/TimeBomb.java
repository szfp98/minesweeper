package minefield;

/**
 *
 */
public class TimeBomb extends Bomb{
    private int explodingTime;

    public TimeBomb(int explodingTime){
        if(!(explodingTime<0))
            this.explodingTime=explodingTime;
        else
            throw new IllegalArgumentException("Exploding time must have a positive value.");
    }
    public void setExplodingTime(int explodingTime) throws IllegalArgumentException{
        if(!(explodingTime<0))
            this.explodingTime=explodingTime;
        else
            throw new IllegalArgumentException("Exploding time must have a positive value.");
    }
}
