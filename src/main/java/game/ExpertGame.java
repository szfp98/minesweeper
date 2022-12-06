package game;

import minefield.ExpertMinefield;

public class ExpertGame extends Game{
    public ExpertGame(boolean timeBombsEnabled, boolean questionMarksEnabled){
        minefield=new ExpertMinefield(timeBombsEnabled);
        this.questionMarksEnabled=questionMarksEnabled;
        level="expert";
    }
}
