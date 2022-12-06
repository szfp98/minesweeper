package game;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Az eltelt és hátralevő idő számításához használt osztály.
 */
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

    /**
     * A számláló elindítása
     */
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

    /**
     * @param value A számlálót felfelé számlálásról lefelé állítva a megadott robbanási időt számolja vissza
     */
    public void countDown(int value){
        this.value=value;
        direction=false;
    }

    /**
     * Megfelezi a visszaszámlálás időtartamát
     */
    public void setHalfTime(){
        countDown(value/2);
    }
    public int getElapsedTime(){
        return elapsedTime;
    }

    public boolean getDirection(){return direction;}

    public void stopTimeCounter(){
        timer.cancel();
        timer.purge();
    }

}
