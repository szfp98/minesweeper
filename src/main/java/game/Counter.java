package game;

public abstract class Counter {
    protected int value;
    public int getValue(){return value;}
    public void increase(int value){
        if(value>=0)
            this.value+=value;
    }
    public void decrease(int value){
        if(value>=0)
            this.value-=value;
    }
}
