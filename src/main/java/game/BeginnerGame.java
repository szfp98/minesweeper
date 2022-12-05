package game;

import minefield.BeginnerMinefield;

public class BeginnerGame extends Game{

    public BeginnerGame(boolean timeBombsEnabled){
        minefield=new BeginnerMinefield(timeBombsEnabled);
        level="beginner";
    }
}
