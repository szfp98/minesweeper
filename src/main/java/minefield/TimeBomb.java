package minefield;

import game.Controller;
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
        this.isTimeBomb=true;
    }

    public void explode(){
        if(timeCounter.getDirection()){
            timeCounter.countDown(explodingTime);
        } else if (!timeCounter.getDirection()&&timeCounter.getValue()>0) {
            timeCounter.setHalfTime();
        } else if(timeCounter.getValue()<=0)
            Controller.gameOver();
    }
}
