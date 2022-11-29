package minefield;

import java.util.ArrayList;

/**
 *
 */
public class FieldList {
    private ArrayList<ArrayList<Field>> fields;
    private Size size;

    /**
     * @param size
     */
    public FieldList(Size size){
        this.size=size;
        fields=new ArrayList<>();
        for(int i=0; i< size.getY(); i++){
            fields.add(new ArrayList<>());
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
        if(rowIndex<=size.getY())
            if(rowIndex>=0)
                return fields.get(rowIndex);
            else
                return null;
        else
            throw new ArrayIndexOutOfBoundsException("Row index overflow");
    }

    /**
     * @param rowOfFields
     * @throws ArrayIndexOutOfBoundsException
     */
    public void addRow(ArrayList<Field> rowOfFields) throws ArrayIndexOutOfBoundsException {
        if(rowOfFields.size()==size.getX()){
            try{
                fields.add(rowOfFields);
            } catch (Exception e){
                throw new ArrayStoreException("Adding row of fields to the fields' list failed: "+e.getMessage());
            }
        } else{
            throw new ArrayIndexOutOfBoundsException("The row size does not fit to the fields' list.");
        }
    }

    /**
     * @param rowIndex
     * @param newRow
     * @throws ArrayIndexOutOfBoundsException
     */
    public void setRow(int rowIndex, ArrayList<Field> newRow) throws ArrayIndexOutOfBoundsException{
        if(rowIndex<=size.getY()){
            if(getRow(rowIndex).isEmpty()){
                fields.add(newRow);
            } else{
                fields.set(rowIndex, newRow);
            }
        } else {
            throw new ArrayIndexOutOfBoundsException("Row index overflow");
        }
    }
}
