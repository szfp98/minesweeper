package minefield;

import java.util.ArrayList;
import java.util.Random;

public abstract class Minefield {
    private FieldList fields;
    private int basicBombs;
    private int timeBombs;
    private ArrayList<Position> bombPositions;

    private int newRandomNumber(Random random, int bound, int[] exceptions){
        int ret=random.nextInt(bound+1-exceptions.length);
        for(int ex:exceptions){
            if(ret<ex)
                break;
            ret++;
        }
        return ret;
    }

    private Position[] setBombPositions(int amount, ArrayList<Position> exceptions){
        int[] xExceptions=new int[exceptions.size()];
        int[] yExceptions=new int[exceptions.size()];
        for(int i=0; i<exceptions.size(); i++){
            xExceptions[i]=exceptions.get(i).getX();
            yExceptions[i]=exceptions.get(i).getY();
        }
        Position[] ret=new Position[amount];
        Random random=new Random();
        for(int i=0; i<amount; i++){
            int x=newRandomNumber(random, fields.getSize().getX()+1, xExceptions);
            int y=newRandomNumber(random, fields.getSize().getY(), yExceptions);
            ret[i]=new Position(x, y);
        }
        return ret;
    }

    private void createBombFields() throws ArrayIndexOutOfBoundsException{
        try{
            Position[] basicBombPositions=setBombPositions(basicBombs, bombPositions);
            for(Position pos:basicBombPositions){
                ArrayList<Field> row=fields.getRow(pos.getY());
                row.add(pos.getX(), new Field(pos, new Bomb()));
                fields.setRow(pos.getY(), row);
                bombPositions.add(pos);
            }
            if(timeBombs>0){
                Position[] timeBombPositions=setBombPositions(timeBombs, bombPositions);
                for(Position pos : timeBombPositions){
                    ArrayList<Field> row=fields.getRow(pos.getY());
                    row.add(pos.getX(), new Field(pos, new TimeBomb()));
                    fields.setRow(pos.getY(), row);
                    bombPositions.add(pos);
                }
            }
        } catch (ArrayIndexOutOfBoundsException e){
            throw new ArrayIndexOutOfBoundsException(e.getMessage());
        }
    }

    public void generateMinefield(FieldList fields, int basicBombs, int timeBombs){
        
    }
}
