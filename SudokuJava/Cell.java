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
    //printDomain();

  }

  public void printDomain(){
    System.out.println( "" );
    System.out.print( " row " + this.row + " column " + this.column + ": " );
    for (int i = 0; i < this.Domain.size(); i++) {
      System.out.print(this.Domain.get(i)+" ");
    }
    System.out.println("");
  }

  public boolean equals(Cell test){
    if(getRow() == test.getRow() && getColumn() == test.getColumn()){
      return true;
    }
    return false;
  }

  public void constrainDomain(ArrayList<Integer> inputDomain){
    //System.out.println( " break " );
    for (int i = 0; i < inputDomain.size(); i++) {
      int value = inputDomain.get(i);
      if(this.Domain.contains(value)){
          removeFromDomain(value);
      }
    }
  }

  public int getDomainVal(){
    if(Domain.size() > 0){
      return Domain.get(0);
    }else{
      System.out.println( "im less" );
      return Domain.size();
    }
  }

  public void removeFromDomain(int input){
    //printDomain();
    this.Domain.remove(this.Domain.indexOf(input));
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

  public void setDomain(ArrayList<Integer> inputDomain) {
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
