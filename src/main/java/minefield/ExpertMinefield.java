package minefield;

public class ExpertMinefield extends Minefield{
    public ExpertMinefield(boolean timeBombsEnabled){
        fields=new FieldList(new Size(30, 16));
        explodingTime=300;
        if(timeBombsEnabled){
            basicBombs=69;
            timeBombs=30;
        } else{
            basicBombs=99;
            timeBombs=0;
        }
    }
}
