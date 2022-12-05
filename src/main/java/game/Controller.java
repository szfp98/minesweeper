package game;

import minefield.Position;

import java.io.IOException;

public class Controller {
    public static ResultList resultList=new ResultList();
    public static Game game;

    public static void saveResult(){
        try{
            Result result=game.getResult();
            if(result!=null){
                resultList.addResult(result);
                resultList.saveToJson();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getResultList(){
        try{
            resultList.loadFromJson();
            return resultList.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
