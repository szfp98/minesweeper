package frontend_test;

import frontend.ResultsFrame;
import org.junit.Test;

public class ResultsFrameTest {
    @Test
    public void initializeResultsFrame() throws InterruptedException {
        String[][] data={
                {"1.", "01", "beginner", "2022. dec. 05."},
                {"1.", "01", "beginner", "2022. dec. 05."}
        };
        ResultsFrame resultsFrame=new ResultsFrame(data);
        Thread.sleep(30000);
    }
}
