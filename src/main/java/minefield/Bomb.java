package minefield;

import game.Controller;

/**
 *Egy egyszerű bombát reprezentáló osztály
 */
public class Bomb {
    protected boolean isTimeBomb;

    public Bomb(){
        isTimeBomb=false;
    }
    public boolean isTimeBomb(){
        return isTimeBomb;
    }
    /**
     *A bombát rejtő mező felfedésekor a bomba felrobban, ezzel vége a játéknak.
     */
    public void explode(){
        Controller.gameOver();
    }
}
