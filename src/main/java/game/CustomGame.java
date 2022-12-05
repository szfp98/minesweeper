package game;

import minefield.CustomMinefield;
import minefield.Size;

public class CustomGame extends Game{
    public CustomGame(int width, int heighth, int basicBombs, int timeBombs, int explodingTime){
        minefield=new CustomMinefield(new Size(width, heighth), basicBombs, timeBombs, explodingTime);
        level="custom";
    }
}
