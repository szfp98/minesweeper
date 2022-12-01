package minefield;

public class CustomMinefield extends Minefield{
    public CustomMinefield(Size size, int basicBombs, int timeBombs, int explodingTime){
        fields=new FieldList(size);
        this.basicBombs=basicBombs;
        this.timeBombs=timeBombs;
        this.explodingTime=explodingTime;
    }
}
