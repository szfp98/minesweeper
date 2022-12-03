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
    public boolean setFlag(boolean value){
        if(!isOpened){
            if(value&&hasQuestionMark){
                hasQuestionMark=false;
            }
            hasFlag=value;
            return hasFlag;
        } else
            return false;
    }

    /**
     * @param value
     */
    public boolean setQuestionMark(boolean value){
        if(!isOpened){
            if(value&&hasFlag)
                hasFlag=false;
            hasQuestionMark=value;
            return hasQuestionMark;
        } else
            return false;
    }
    public int open() throws IllegalAccessException {
        if(!isOpened&&!hasFlag&&!hasQuestionMark){
            if(bomb==null){
                isOpened=true;
                return value;
            }
            else{
                bomb.explode();
                return -1;
            }
        } else
            throw new IllegalAccessException("Field is marked.");
    }
}
