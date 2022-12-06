package minefield;

/**
 * A játék atomi egysége.
 */
public class Field {
    //private final Position position;
    private final int value;
    private final Bomb bomb;
    private boolean isOpened;
    private boolean hasFlag;
    private boolean hasQuestionMark;

    /**
     * @param position A játéktáblán elhelyezkedő mező x,y pozíciója
     * @param value A szomszédos aknamezők száma
     */
    public Field(Position position, int value) {
        //this.position=position;
        this.value=value;
        bomb=null;
        isOpened=false;
        hasFlag=false;
        hasQuestionMark=false;
    }

    /**
     * @param position A játéktáblán elhelyezkedő mező x,y pozíciója
     * @param bomb A mezőn elrejtett bomba
     */
    public Field(Position position, Bomb bomb){
        //this.position=position;
        this.value=0;
        this.bomb=bomb;
        this.isOpened=false;
        this.hasFlag=false;
        this.hasQuestionMark=false;
    }

    public int getValue(){
        if(bomb!=null)
            return -1;
        else
            return value;
    }

    public Bomb getBomb(){
        return bomb;
    }


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


    public boolean setQuestionMark(boolean value){
        if(!isOpened){
            if(value&&hasFlag)
                hasFlag=false;
            hasQuestionMark=value;
            return hasQuestionMark;
        } else
            return false;
    }

    /**
     * @return A mezőt megnyitva megkapjuk a mező értékét. Ha a mezőn bomba van, -1-et ad vissza.
     */
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

    public boolean isOpened(){
        return isOpened;
    }
}
