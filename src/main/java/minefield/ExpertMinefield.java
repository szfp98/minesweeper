package minefield;

public class ExpertMinefield extends Minefield{
    /**
     * Az absztrakt Minefield osztály egy példányát létrehozó konstruktor, haladó pályához
     * @param timeBombsEnabled Igaz, ha a játékban engedélyezzük az időzített bombák használatát
     */
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
