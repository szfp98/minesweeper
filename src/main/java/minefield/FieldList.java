package minefield;

import java.util.ArrayList;

public class FieldList {
    private ArrayList<ArrayList<Field>> fields;
    private Size size;
    public FieldList(Size size){
        this.size=size;
        fields=new ArrayList<>();
    }

    public Size getSize() {
        return size;
    }

    public ArrayList<Field> getRow(int rowIndex) throws ArrayIndexOutOfBoundsException{
        try{
            return fields.get(rowIndex);
        } catch (Exception e){
            throw new ArrayIndexOutOfBoundsException("Getting row of fields from the fields' list failed: "+e.getMessage());
        }
    }
    public void addRow(ArrayList<Field> rowOfFields) throws RuntimeException {
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
}
