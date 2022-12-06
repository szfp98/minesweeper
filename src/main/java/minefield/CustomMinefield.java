package minefield;

public class CustomMinefield extends Minefield{
    /**
     *Az absztrakt Minefield osztály egy példányát létrehozó konstruktor, egyéni beállítású pályához
     * @param size A pálya mérete
     * @param basicBombs Az egyszerű bombák száma
     * @param timeBombs Az időzített bombák száma
     * @param explodingTime Az időzített bombák robbanási ideje
     */
    public CustomMinefield(Size size, int basicBombs, int timeBombs, int explodingTime){
        if(size.getX()* size.getY()>=basicBombs+timeBombs){
            fields=new FieldList(size);
            this.explodingTime=explodingTime;
            this.basicBombs=basicBombs;
            this.timeBombs=timeBombs;
        } else
            throw new IllegalArgumentException("Invalid minefield parameters.");
    }
}
