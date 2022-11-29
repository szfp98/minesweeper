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
     * @throws IllegalAccessException
     */
    public void setFlag(boolean value) throws IllegalAccessException {
        if(!isOpened){
            hasFlag=value;
        } else {
            throw new IllegalAccessException("This field is already opened.");
        }
    }

    /**
     * @param value
     * @throws IllegalAccessException
     */
    public void setQuestionMark(boolean value) throws IllegalAccessException {
        if(!isOpened){
            hasQuestionMark=value;
        } else{
            throw new IllegalAccessException("This field is already opened.");
        }
    }
}
