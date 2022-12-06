package game;

import frontend.GameFrame;
import minefield.Position;

import java.io.IOException;

/**
 * A játékot vezérlő statikus kontrollerosztály
 */
public class Controller {
    public static ResultList resultList=new ResultList();
    public static Game game;
    public static GameFrame gameFrame;
    public static boolean gameIsOn;

    public static int getMineCounterValue(){
        try{
            if(gameIsOn){
                return game.mineCounter.getValue();
            } else return 0;
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public static int getTimeCounterValue(){
        try{
            if(gameIsOn){
                return game.timeCounter.getValue();
            } else return 0;
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public static boolean questionMarkField(int x, int y){
        try {
            if(gameIsOn&&game.questionMarksEnabled){
                return game.minefield.questionMarkField(new Position(x, y));
            } else
                throw new IllegalAccessException("Question marks not enabled.");
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public static boolean removeQuestionMark(int x, int y){
        try{
            if(gameIsOn){
                return game.minefield.removeQuestionMark(new Position(x, y));
            } else
                throw new IllegalAccessException("No game is running.");
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public static boolean flagField(int x, int y){
        try{
            if(gameIsOn){
                game.mineCounter.decrease(1);
                return game.minefield.flagField(new Position(x, y));
            } else
                throw new IllegalAccessException("No game is running.");
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public static boolean removeFlag(int x, int y){
        try{
            if(gameIsOn){
                return game.minefield.removeFlag(new Position(x, y));
            } else
                throw new IllegalAccessException("No game is running.");
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public static int openField(int x, int y){
        try{
            if(gameIsOn){
                return game.minefield.openField(new Position(x, y));
            }
            else throw new IllegalAccessException("No game is running.");
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Új játékot indító és játéktáblát létrehozó metódus
     * @param level A játék szintje
     * @param timeBombsEnabled Időzített bombák engedélyezése
     * @param questionMarksEnabled Kérdőjeles megjelölés engedélyezése
     */
    public static void newGame(String level, boolean timeBombsEnabled, boolean questionMarksEnabled){
        try{
            if(gameIsOn){
                gameOver();
            }
            if(level.equalsIgnoreCase("beginner")){
                game=new BeginnerGame(timeBombsEnabled, questionMarksEnabled);
            } else if(level.equalsIgnoreCase("intermediate")){
                game=new IntermediateGame(timeBombsEnabled, questionMarksEnabled);
            } else if (level.equalsIgnoreCase("expert")) {
                game=new ExpertGame(timeBombsEnabled, questionMarksEnabled);
            }
            if(gameFrame!=null){
                gameFrame.dispose();
            }
            game.startGame();
            gameFrame=new GameFrame(game.minefield.getWidth(), game.minefield.getHeight());
            gameIsOn=true;
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * A játék végén meghívódó metódus. Ha a játékos nyert, a játék adatait fájlba menti.
     */
    public static void gameOver(){
        try{
            if(gameIsOn){
                if(game.endGame()){
                    saveResult();
                    gameFrame.sendMessage("You won!");
                }
                else{
                    gameFrame.sendMessage("You lose!");
                }
                gameFrame.revealFields();
                gameIsOn=false;
            }
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

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
