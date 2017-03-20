import java.util.ArrayList;

public class Cell{
  //The sudoku grid is made of cells
  private ArrayList<Integer> Domain = new ArrayList<Integer>();
  private Boolean  isPreset = false;
  private Integer row;
  private Integer column;

  public Cell(ArrayList<Integer> inputDomain, Integer inputRow, Integer inputCol){
    setDomain(inputDomain);
    setRow(inputRow);
    setColumn(inputCol);

  }

  public ArrayList<Integer> getDomain(){
    return this.Domain;
  }

  public Boolean getPreset(){
    return this.isPreset;
  }

  private void setPreset(Boolean inputPreset){
    this.isPreset = inputPreset;
  }

  private void setDomain(ArrayList<Integer> inputDomain) {
    this.Domain = inputDomain;
  }

  public Integer getRow(){
    return this.row;
  }

  private void setRow(Integer inputRow) {
   this.row = inputRow;
   }

  private void setColumn(Integer inputCol) {
   this.column = inputCol;
  }

  public Integer getColumn() {
   return this.column;
  }




}
