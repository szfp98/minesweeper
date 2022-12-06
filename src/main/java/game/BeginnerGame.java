package game;

import minefield.BeginnerMinefield;

public class BeginnerGame extends Game{

    public BeginnerGame(boolean timeBombsEnabled, boolean questionMarksEnabled){
        minefield=new BeginnerMinefield(timeBombsEnabled);
        this.questionMarksEnabled=questionMarksEnabled;
        level="beginner";
    }
}
