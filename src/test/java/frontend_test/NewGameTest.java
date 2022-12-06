package frontend_test;

import game.Controller;
import org.junit.Test;

public class NewGameTest {
    @Test
    public void init() throws InterruptedException {
        Controller.newGame("beginner", true, true);
        Thread.sleep(300000);
        System.out.println("suna");
    }
}
