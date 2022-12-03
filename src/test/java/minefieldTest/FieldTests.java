package minefieldTest;

import minefield.Bomb;
import minefield.Field;
import minefield.Position;
import minefield.TimeBomb;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FieldTests {
    Field numberField;
    Field basicBombField;
    Field timeBombField;
    @Before
    public void init(){
        numberField=new Field(new Position(0,0), 2);
        basicBombField=new Field(new Position(1,0), new Bomb());
        timeBombField=new Field(new Position(0,1), new TimeBomb(5000));
    }

    @Test
    public void testQuestionMark(){
        Assert.assertTrue(numberField.setQuestionMark(true));
    }
    @Test
    public void testOpenNumberField() throws IllegalAccessException {
        numberField.open();
    }
    @Test(expected = IllegalAccessException.class)
    public void openMarkedField() throws IllegalAccessException {
        testOpenNumberField();
        testOpenNumberField();
    }
}
