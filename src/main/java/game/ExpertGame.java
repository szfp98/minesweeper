package game;

import minefield.ExpertMinefield;

public class ExpertGame extends Game{
    public ExpertGame(boolean timeBombsEnabled){
        minefield=new ExpertMinefield(timeBombsEnabled);
        level="expert";
    }
}
