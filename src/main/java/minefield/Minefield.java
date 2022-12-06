package minefield;

import game.TimeCounter;

import java.util.*;

/**
 * Az aknamezőt reprezentáló absztrakt osztály
 */
public abstract class Minefield {
    protected FieldList fields;
    protected int basicBombs;
    protected int timeBombs;
    protected int explodingTime;
    private ArrayList<Position> bombPositions;

    public int getBombPositionsSize(){
        if(!bombPositions.isEmpty())
            return bombPositions.size();
        else
            return 0;
    }

    /**
     * @param random A Random osztály egy példánya
     * @param bound A generálandó random szám felső határa
     * @param exceptions A tömbben szereplő számok helyett újat generál
     * @return Visszaad egy random számokból generált tömböt.
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

    /**
     * @param amount Ennyi pozíció jön létre a bombák számára
     * @return Véletlenszerűen generált pozíciók tömbje
     */
    private Position[] setBombPositions(int amount){
        try{
            Random random=new Random();
            Position[] ret=new Position[amount];
            if(!bombPositions.isEmpty()){
                ArrayList<int[]> posByRow=new ArrayList<>();
                for(int i=0; i<fields.getSize().getY(); i++){
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
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    private void removeDuplicatesFromBombPositions(){
        HashSet<Position> hashSet= new HashSet<>(bombPositions);
        bombPositions=new ArrayList<>(hashSet);
    }


    /**
     * Ez a metódus helyezi el a bombákat az aknamezőn.
     * @param tc Az időzített bombákhoz szükséges időzítő
     * @throws ArrayIndexOutOfBoundsException
     * @throws ArrayStoreException
     */
    private void createBombFields(TimeCounter tc) throws ArrayIndexOutOfBoundsException, ArrayStoreException{
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
                    row.set(pos.getX(), new Field(pos, new TimeBomb(tc, explodingTime)));
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

    /**
     * @return Visszaad egy kétdimenziós tömböt, amiben az aknamezőnek megfelelő helyeken szerepelnek a szomszédos bombákat reprezentáló értékek.
     */
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

    /**
     * Az aknamezőt generáló metódus
     * @param tc Az időzített bombákhoz szükséges időzítő
     */
    public void generateMinefield(TimeCounter tc){
        try{
            createBombFields(tc);
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

    /**
     * @param position A felfedni kívánt mezőt azonosító x, y koordináták
     * @return Visszatér a felfedett mező értékével. Ha bomba van a mezőn, akkor -1-et ad vissza.
     * @throws IllegalAccessException
     */
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

    /**
     * @param position A zászlóval megjelölni kívánt mező pozíciója
     * @return Igaz, ha sikerült a mező megjelölése
     */
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

    /**
     * @param position Zászló levétele a paraméterben megadott pozíción
     * @return Hamis, ha sikerült a zászló eltávolítása
     */
    public boolean removeFlag(Position position){
        try{
            ArrayList<Field> row=fields.getRow(position.getY());
            Field field=row.get(position.getX());
            boolean value=field.setFlag(false);
            row.set(position.getX(), field);
            fields.setRow(position.getY(), row);
            return value;
        } catch (ArrayIndexOutOfBoundsException | ArrayStoreException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * @param position A kérdőjellel megjelölni kívánt mezőt azonosító pozíció
     * @return Igaz, ha sikerült a megjelölés
     */
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

    public boolean removeQuestionMark(Position position){
        try{
            ArrayList<Field> row=fields.getRow(position.getY());
            Field field=row.get(position.getX());
            boolean value=field.setQuestionMark(false);
            row.set(position.getX(), field);
            fields.setRow(position.getY(), row);
            return value;
        } catch (ArrayIndexOutOfBoundsException | ArrayStoreException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * @return Igaz, ha az aknamezőn csak számot vagy időzített bombát tartalamzó mezők vannak felfedve.
     */
    public boolean checkWin(){
        try{
            int count=0;
            for(int i=0; i<fields.getSize().getY(); i++){
                ArrayList<Field> row=fields.getRow(i);
                for(int j=0; j<fields.getSize().getX(); j++){
                    Field field=row.get(j);
                    if(field.isOpened()&&(field.getBomb().isTimeBomb()||field.getBomb()==null))
                        count++;
                }
            }
            return fields.getSize().getX() * fields.getSize().getY() - count == getBombPositionsSize() - getTimeBombs();
        } catch (ArrayIndexOutOfBoundsException e){
        throw new ArrayIndexOutOfBoundsException(e.getMessage());
    }
    }

    /**
     * @return Visszatér az aknamező értékeit reprezentáló kétdimenziós tömbbel. A bombák helyén -1 érték van.
     * @throws ArrayIndexOutOfBoundsException
     */
    public int[][] getAllFieldsValues() throws ArrayIndexOutOfBoundsException{
        try {
            int[][] fieldsValues=new int[fields.getSize().getY()][fields.getSize().getX()];
            for(int i=0; i<fields.getSize().getY(); i++){
                ArrayList<Field> row=fields.getRow(i);
                for(int j=0; j<fields.getSize().getX(); j++){
                    fieldsValues[i][j]=row.get(j).getValue();
                }
            }
            return fieldsValues;
        } catch (ArrayIndexOutOfBoundsException e){
            throw new ArrayIndexOutOfBoundsException(e.getMessage());
        }
    }

    public int getTimeBombs(){
        try{
            int n=0;
            for(Position p: bombPositions){
                ArrayList<Field> row=fields.getRow(p.getY());
                Field field=row.get(p.getX());
                Bomb bomb=field.getBomb();
                if(bomb!=null&&bomb.isTimeBomb())
                    n++;
            }
            return n;
        } catch (ArrayIndexOutOfBoundsException e){
            throw new ArrayIndexOutOfBoundsException(e.getMessage());
        }
    }

    public int getWidth(){
        return fields.getSize().getX();
    }
    public int getHeight(){
        return fields.getSize().getX();
    }
}
