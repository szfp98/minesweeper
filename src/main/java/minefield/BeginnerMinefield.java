package minefield;

public class BeginnerMinefield extends Minefield{
    public BeginnerMinefield(boolean timeBombsEnabled){
        fields=new FieldList(new Size(8, 8));
        explodingTime=300;
        if(timeBombsEnabled){
            basicBombs=5;
            timeBombs=5;
        } else{
            basicBombs=10;
            timeBombs=0;
        }
    }
}
