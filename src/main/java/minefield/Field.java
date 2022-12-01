package minefield;

/**
 *
 */
public class Field {
    private final Position position;
    private final int value;
    private final Bomb bomb;
    private boolean isOpened;
    private boolean hasFlag;
    private boolean hasQuestionMark;

    /**
     * @param position
     * @param value
     */
    public Field(Position position, int value) {
        this.position=position;
        this.value=value;
        bomb=null;
        isOpened=false;
        hasFlag=false;
        hasQuestionMark=false;
    }

    /**
     * @param position
     * @param bomb
     */
    public Field(Position position, Bomb bomb){
        this.position=position;
        this.value=0;
        this.bomb=bomb;
        this.isOpened=false;
        this.hasFlag=false;
        this.hasQuestionMark=false;
    }

    /**
     * @param value
     */
    public void setFlag(boolean value){
        if(!isOpened){
            hasFlag=value;
        }
    }

    /**
     * @param value
     */
    public void setQuestionMark(boolean value){
        if(!isOpened){
            hasQuestionMark=value;
        }
    }
}
