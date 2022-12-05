package gameTest;

import game.Result;
import game.ResultList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

public class ResultTest {
    Result res1;
    Result res2;
    ResultList results;
    @Before
    public void init(){
        res1=new Result(new Date(), "Beginner", 123);
        res2=new Result(new Date(), "intermediate", 234);
        results=new ResultList();
    }
    @Test
    public void sortResultsTest() throws IOException {
        results.addResult(res2);
        results.addResult(res1);
    }
    @Test
    public void saveResultsTest() throws IOException {
        sortResultsTest();
        results.saveToJson();
    }
    @Test
    public void loadResultsTest() throws IOException {
        results=new ResultList();
        results.loadFromJson();
    }
    @Test
    public void toStringTest() throws IOException {
        loadResultsTest();
        String listToString=results.toString();
        System.out.println("suna");
    }
}
