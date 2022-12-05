package minefield;

import game.TimeCounter;

/**
 *
 */
public class TimeBomb extends Bomb{
    private TimeCounter timeCounter;
    private int explodingTime;

    public TimeBomb(TimeCounter timeCounter, int explodingTime){
        this.timeCounter=timeCounter;
        this.explodingTime=explodingTime;
    }

    public void explode(){
        if(timeCounter.getDirection()){
            timeCounter.countDown(explodingTime);
        } else if (!timeCounter.getDirection()) {
            timeCounter.setHalfTime();
        }
    }
}
