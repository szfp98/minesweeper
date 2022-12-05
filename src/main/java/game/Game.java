package game;

import minefield.Minefield;
import minefield.Position;

import java.util.Date;

public abstract class Game {
    protected Minefield minefield;
    protected TimeCounter timeCounter;
    protected MineCounter mineCounter;
    private Result result;
    protected String level;

    public void startGame(){
        try{
            mineCounter=new MineCounter(minefield.getBombPositionsSize());
            timeCounter=new TimeCounter();
            timeCounter.startTimeCounter();
            result=null;
            minefield.generateMinefield(timeCounter);
        } catch (Exception e){
            throw new RuntimeException("Could not start game: "+e.getMessage());
        }
    }

    public boolean endGame(){
        try{
            if(mineCounter.getValue()==0&&timeCounter.getValue()>=0){
                result=new Result(new Date(), level, timeCounter.getElapsedTime());
                return true;
            }
            else
                return false;
        } catch (Exception e){
            throw new RuntimeException("Could not end game: "+e.getMessage());
        }
    }
    public Result getResult(){
        return result;
    }
}
