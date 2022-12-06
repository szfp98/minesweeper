package game;

import minefield.IntermediateMinefield;

public class IntermediateGame extends Game{
    public IntermediateGame(boolean timeBombsEnabled, boolean questionMarksEnabled){
        minefield=new IntermediateMinefield(timeBombsEnabled);
        this.questionMarksEnabled=questionMarksEnabled;
        level="intermediate";
    }
}
