package frontend_test;

import frontend.GameFrame;
import org.junit.Test;

public class GameFrameTest {
    @Test
    public void initializeGameFrame() throws InterruptedException {
        GameFrame gameFrame=new GameFrame(8,8);
        Thread.sleep(30000);
    }
    @Test
    public void printFieldsValues(){
    }
}
