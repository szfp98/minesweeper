package minefield;

public class CustomMinefield extends Minefield{
    public CustomMinefield(Size size, int basicBombs, int timeBombs, int explodingTime){
        if(size.getX()* size.getY()>=basicBombs+timeBombs){
            fields=new FieldList(size);
            this.basicBombs=basicBombs;
            this.timeBombs=timeBombs;
            this.explodingTime=explodingTime;
        } else
            throw new IllegalArgumentException("Invalid minefield parameters.");
    }
}
