package minefield;

public class BeginnerMinefield extends Minefield{
    /**
     * Az absztrakt Minefield osztály egy példányát létrehozó konstruktor, kezdő pályához
     * @param timeBombsEnabled Igaz, ha a játékban engedélyezzük az időzített bombák használatát
     */
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
