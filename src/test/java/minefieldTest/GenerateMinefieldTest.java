package minefieldTest;

import minefield.BeginnerMinefield;
import org.junit.Before;
import org.junit.Test;

public class GenerateMinefieldTest {
    BeginnerMinefield beginnerMinefield;
    @Before
    public void init(){
        beginnerMinefield=new BeginnerMinefield(false);
    }
    @Test
    public void generateBeginner(){
        //beginnerMinefield.generateMinefield();
        System.out.println("suna");
    }
}
