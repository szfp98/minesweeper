package minefield;

public class Field {
    private final Position position;
    private final int value;
    private final Bomb bomb;
    private boolean isOpened;
    private boolean hasFlag;
    private boolean hasQuestionMark;
    public Field(Position position, int value) {
        this.position=position;
        this.value=value;
        bomb=null;
        isOpened=false;
        hasFlag=false;
        hasQuestionMark=false;
    }
    public Field(Position position, Bomb bomb){
        this.position=position;
        this.value=0;
        this.bomb=bomb;
        this.isOpened=false;
        this.hasFlag=false;
        this.hasQuestionMark=false;
    }
    public void setFlag() throws IllegalAccessException {
        if(!isOpened){
            hasFlag=!hasFlag;
        } else {
            throw new IllegalAccessException("This field is already opened.");
        }
    }
    public void setQuestionMark() throws IllegalAccessException {
        if(!isOpened){
            hasQuestionMark=!hasQuestionMark;
        } else{
            throw new IllegalAccessException("This field is already opened.");
        }
    }
}
