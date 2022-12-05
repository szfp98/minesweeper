package game;

import java.util.Timer;
import java.util.TimerTask;

public class TimeCounter extends Counter{
    private int elapsedTime;
    private Timer timer;
    private boolean direction;

    public TimeCounter(){
        value=0;
        timer=new Timer();
        direction=true;
        elapsedTime=0;
    }

    public void startTimeCounter(){
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(value>=0){
                    if(direction){
                        increase(1);
                    }
                    else if(direction==false){
                        decrease(1);
                    }
                    elapsedTime++;
                }
            }
        }, 0, 1000);
    }
    public void countDown(int value){
        this.value=value;
        direction=false;
    }
    public void setHalfTime(){
        countDown(value/2);
    }
    public int getElapsedTime(){
        return elapsedTime;
    }

    public boolean getDirection(){return direction;}

}
