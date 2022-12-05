package minefield;

public class IntermediateMinefield extends Minefield{
    public IntermediateMinefield(boolean timeBombsEnabled){
        fields=new FieldList(new Size(16, 16));
        explodingTime=300;
        if(timeBombsEnabled){
            basicBombs=24;
            timeBombs=16;
        } else{
            basicBombs=40;
            timeBombs=0;
        }
    }
}
