package game;

import minefield.Minefield;

import java.util.Date;

/**
 * Egy aknakereső-játszmát megvalósító osztály
 */
public abstract class Game {
    protected Minefield minefield;
    protected TimeCounter timeCounter;
    protected MineCounter mineCounter;

    protected boolean questionMarksEnabled;
    private Result result;
    protected String level;

    public void startGame(){
        try{
            timeCounter=new TimeCounter();
            minefield.generateMinefield(timeCounter);
            mineCounter=new MineCounter(minefield.getBombPositionsSize());

            timeCounter.startTimeCounter();
            result=null;

        } catch (Exception e){
            throw new RuntimeException("Could not start game: "+e.getMessage());
        }
    }

    /**
     * @return Igaz, ha a játék végén minden, nem aknát rejtő mezőt felfedtünk
     */
    public boolean checkWin(){
        try{
            return mineCounter != null && mineCounter.getValue() == 0 && timeCounter.getValue() >= 0 && minefield.checkWin();
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * A kontroller hívja meg, ha a játéknak vége. Nyertes játék esetén eltárolja az eredményt.
     * @return Igaz, ha a játékos nyert.
     */
    public boolean endGame(){
        try{
            timeCounter.stopTimeCounter();
            if(checkWin()){
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

    public boolean isQuestionMarkEnabled(){
        return questionMarksEnabled;
    }

    public String getLevel(){
        return level;
    }

    public int[][] getFieldsValues(){
        try{
            return minefield.getAllFieldsValues();
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
