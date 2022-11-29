package minefield;

/**
 *
 */
public class Position {
    private final int x;
    private final int y;
    public Position(int x, int y){
        this.x=x;
        this.y=y;
    }

    /**
     * @return
     */
    public int getX(){
        return x;
    }

    /**
     * @return
     */
    public int getY() {
        return y;
    }
}
