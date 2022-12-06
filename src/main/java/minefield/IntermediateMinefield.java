package minefield;

public class IntermediateMinefield extends Minefield{
    /**
     * Az absztrakt Minefield osztály egy példányát létrehozó konstruktor, középhaladó pályához
     * @param timeBombsEnabled Igaz, ha a játékban engedélyezzük az időzített bombák használatát
     */
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
