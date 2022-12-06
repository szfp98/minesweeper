package minefield;

/**
 *Az aknamező méretét reprezentáló x,y számpár
 */
public class Size {
    private final int x;
    private final int y;

    public Size(int x, int y){
        this.x=x;
        this.y=y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
