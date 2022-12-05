package game;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Result {
    private Date date;
    private String level;
    private int time;

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
}
