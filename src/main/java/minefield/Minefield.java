package minefield;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 */
public abstract class Minefield {
    protected FieldList fields;
    protected int basicBombs;
    protected int timeBombs;

    protected int explodingTime;
    private ArrayList<Position> bombPositions;

    /**
     * @param random
     * @param bound
     * @param exceptions
     * @return
     */
    private int newRandomNumber(Random random, int bound, int[] exceptions){
        int ret=random.nextInt(bound+1-exceptions.length);
        for(int ex:exceptions){
            if(ret<ex)
                break;
            ret++;
        }
        return ret;
    }

    /**
     * @param amount
     * @param exceptions
     * @return
     */
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
            int x=newRandomNumber(random, fields.getSize().getX(), xExceptions);
            int y=newRandomNumber(random, fields.getSize().getY(), yExceptions);
            ret[i]=new Position(x, y);
        }
        return ret;
    }

    /**
     * @throws ArrayIndexOutOfBoundsException
     */
    private void createBombFields() throws ArrayIndexOutOfBoundsException, ArrayStoreException{
        try{
            bombPositions=new ArrayList<>();
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
                    row.add(pos.getX(), new Field(pos, new TimeBomb(explodingTime)));
                    fields.setRow(pos.getY(), row);
                    bombPositions.add(pos);
                }
            }
        } catch (ArrayIndexOutOfBoundsException e){
            throw new ArrayIndexOutOfBoundsException(e.getMessage());
        } catch (ArrayStoreException e){
            throw new ArrayStoreException(e.getMessage());
        }
    }

    private int[][] setFieldsValues(){
        int[][] fieldsValues=new int[fields.getSize().getY()][fields.getSize().getX()];
        for(int i=0; i<fields.getSize().getY(); i++){
            for(int j=0; j<fields.getSize().getX(); j++){
                fieldsValues[i][j]=0;
            }
        }
        for(Position p:bombPositions){
            for(int i=p.getY()-1; i<=p.getY()+1; i++){
                for(int j=p.getX()-1; i<= p.getX()+1; j++){
                    if(!(i== p.getY()&&j==p.getX()))
                        fieldsValues[i][j]++;
                    else
                        fieldsValues[i][j]=-1;
                }
            }
        }
        return fieldsValues;
    }

    public void generateMinefield(){
        try{
            createBombFields();
            int[][] fieldsValues=setFieldsValues();
            for(int i=0; i<fields.getSize().getY(); i++){
                ArrayList<Field> row=fields.getRow(i);
                for(int j=0; j<fields.getSize().getX(); j++){
                    Position pos=new Position(j, i);
                    if(!bombPositions.contains(pos)){
                        row.add(j, new Field(pos, fieldsValues[j][i]));
                    }
                }
                fields.setRow(i, row);
            }
        } catch (ArrayStoreException | ArrayIndexOutOfBoundsException e){
            throw new RuntimeException("Creation of the minefield failed: "+e.getMessage());
        }
    }
}
