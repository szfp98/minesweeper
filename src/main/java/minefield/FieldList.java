package minefield;

import java.util.ArrayList;

/**
 *
 */
public class FieldList {
    private ArrayList<ArrayList<Field>> fields;
    private final Size size;

    /**
     * @param size
     */
    public FieldList(Size size){
        this.size=size;
        fields=new ArrayList<>();
        for(int i=0; i< size.getY(); i++){
            ArrayList<Field> row=new ArrayList<>();
            for(int j=0; j< size.getX(); j++){
                row.add(null);
            }
            fields.add(row);
        }
    }

    /**
     * @return
     */
    public Size getSize() {
        return size;
    }

    /**
     * @param rowIndex
     * @return
     * @throws ArrayIndexOutOfBoundsException
     */
    public ArrayList<Field> getRow(int rowIndex) throws ArrayIndexOutOfBoundsException{
        if(rowIndex<size.getY()&&rowIndex>=0)
            return fields.get(rowIndex);
        else
            throw new ArrayIndexOutOfBoundsException("Row index overflow");
    }

    /**
     * @param rowIndex
     * @param newRow
     * @throws ArrayIndexOutOfBoundsException
     */
    public void setRow(int rowIndex, ArrayList<Field> newRow) throws ArrayIndexOutOfBoundsException, ArrayStoreException{
        try{
            if(rowIndex<size.getY()){
                fields.set(rowIndex, newRow);
            } else {
                throw new ArrayIndexOutOfBoundsException("Row index overflow");
            }
        } catch(Exception e){
            throw new ArrayStoreException("Modifying the fields' list failed: "+e.getMessage());
        }
    }
}
