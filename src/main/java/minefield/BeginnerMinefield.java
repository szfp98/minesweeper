package minefield;

public class BeginnerMinefield extends Minefield{
    public BeginnerMinefield(boolean timeBombsEnabled){
        fields=new FieldList(new Size(8, 8));
        this.explodingTime=300000;
        if(timeBombsEnabled){
            basicBombs=5;
            timeBombs=5;
        } else{
            basicBombs=10;
            timeBombs=0;
        }
    }
}
