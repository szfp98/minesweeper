package minefield;

import java.util.*;

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
        int ret=random.nextInt(bound-exceptions.length);
        if(exceptions.length>0){
            for(int ex:exceptions){
                if(ret<ex)
                    break;
                ret++;
            }
        }
        return ret;
    }

    private Position[] setBombPositions(int amount){
        Random random=new Random();
        Position[] ret=new Position[amount];
        if(!bombPositions.isEmpty()){
            ArrayList<int[]> posByRow=new ArrayList<>();
            for(int i=0; i<bombPositions.size(); i++){
                posByRow.add(new int[0]);
            }
            for(Position p:bombPositions){
                int[] row=posByRow.get(p.getY());
                int[] newRow=new int[row.length+1];
                System.arraycopy(row, 0, newRow, 0, row.length);
                newRow[row.length]=p.getX();
                posByRow.set(p.getY(), newRow);
            }
            for(int i=0; i<amount; i++){
                int y=random.nextInt(fields.getSize().getY());
                int x=newRandomNumber(random, fields.getSize().getX(), posByRow.get(y));
                ret[i]=new Position(x, y);
            }
        } else{
            for (int i=0; i<amount; i++){
                int y=random.nextInt(fields.getSize().getY());
                int x=random.nextInt(fields.getSize().getX());
                ret[i]=new Position(x, y);
            }
        }
        return ret;
    }

    private void removeDuplicatesFromBombPositions(){
        HashSet<Position> hashSet= new HashSet<>(bombPositions);
        bombPositions=new ArrayList<>(hashSet);
    }

    /**
     * @throws ArrayIndexOutOfBoundsException
     */
    private void createBombFields() throws ArrayIndexOutOfBoundsException, ArrayStoreException{
        try{
            bombPositions=new ArrayList<>();
            Position[] basicBombPositions=setBombPositions(basicBombs);
            for(Position pos:basicBombPositions){
                ArrayList<Field> row=fields.getRow(pos.getY());
                row.set(pos.getX(), new Field(pos, new Bomb()));
                fields.setRow(pos.getY(), row);
                bombPositions.add(pos);
            }
            if(timeBombs>0){
                Position[] timeBombPositions=setBombPositions(timeBombs);
                for(Position pos : timeBombPositions){
                    ArrayList<Field> row=fields.getRow(pos.getY());
                    row.set(pos.getX(), new Field(pos, new TimeBomb(explodingTime)));
                    fields.setRow(pos.getY(), row);
                    bombPositions.add(pos);
                }
            }
            removeDuplicatesFromBombPositions();
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
                for(int j=p.getX()-1; j<= p.getX()+1; j++){
                    if(i>=0&&i<fields.getSize().getY()&&j>=0&&j<fields.getSize().getX()&&fieldsValues[i][j]!=-1){
                        if(i==p.getY()&&j==p.getX())
                            fieldsValues[i][j]=-1;
                        else
                            fieldsValues[i][j]++;
                    }
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
                    if(fieldsValues[i][j]>=0){
                        row.set(j, new Field(pos, fieldsValues[i][j]));
                    }
                }
                fields.setRow(i, row);
            }
        } catch (ArrayStoreException | ArrayIndexOutOfBoundsException e){
            throw new RuntimeException("Creation of the minefield failed: "+e.getMessage());
        }
    }

    public int openField(Position position) throws IllegalAccessException {
        try{
            ArrayList<Field> row=fields.getRow(position.getY());
            Field field=row.get(position.getX());
            int value=field.open();
            row.set(position.getX(), field);
            fields.setRow(position.getY(), row);
            return value;
        } catch (IllegalAccessException e){
            throw new IllegalAccessException(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException | ArrayStoreException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public boolean flagField(Position position){
        try{
            ArrayList<Field> row=fields.getRow(position.getY());
            Field field=row.get(position.getX());
            boolean value=field.setFlag(true);
            row.set(position.getX(), field);
            fields.setRow(position.getY(), row);
            return value;
        } catch (ArrayIndexOutOfBoundsException | ArrayStoreException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public boolean questionMarkField(Position position){
        try{
            ArrayList<Field> row=fields.getRow(position.getY());
            Field field=row.get(position.getX());
            boolean value=field.setQuestionMark(true);
            row.set(position.getX(), field);
            fields.setRow(position.getY(), row);
            return value;
        } catch (ArrayIndexOutOfBoundsException | ArrayStoreException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
