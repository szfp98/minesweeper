package game;

import minefield.IntermediateMinefield;

public class IntermediateGame extends Game{
    public IntermediateGame(boolean timeBombsEnabled){
        minefield=new IntermediateMinefield(timeBombsEnabled);
        level="intermediate";
    }
}
