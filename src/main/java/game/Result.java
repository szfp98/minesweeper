package game;

import java.util.Date;

public class Result {
    private final Date date;
    private final String level;
    private final int time;

    /**
     * Egy győztes játék eredményét reprezentáló osztály konstruktora
     * @param date A játék dátuma
     * @param level A játék szintje
     * @param time A végigjátszás ideje
     */
    public Result(Date date, String level, int time){
        if(date.equals(new Date())||date.before(new Date()))
            this.date=date;
        else
            throw new IllegalArgumentException("Date of ended game must be earlier.");
        if(level.equalsIgnoreCase("beginner")||
                level.equalsIgnoreCase("intermediate")||
                level.equalsIgnoreCase("expert")||
                level.equalsIgnoreCase("custom"))
            this.level=level.toLowerCase();
        else
            throw new IllegalArgumentException("Not valid game level");
        if(time>=0)
            this.time=time;
        else
            throw new IllegalArgumentException("Time must be higher than 0.");
    }
    public String toString(){
        return time + '\t' + level + '\t' + date;
    }
    public int getTime(){
        return time;
    }

    public String getLevel(){return level;}
    public Date getDate(){return date;}
}
