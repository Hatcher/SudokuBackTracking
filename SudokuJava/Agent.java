import java.util.ArrayList;
import java.util.*;

public class Agent{
  /*
  * Create an agent who stores our input grid.
  */
  private Integer[][] startPuzzle;
  private Cell[][] cellGrid;
  private LinkedList<Arc> queueConstraints; //Has queue for storing the pairs of constrained cells
  private Grid grid;
  public Agent(Integer[][] inputGrid){
    this.queueConstraints = new LinkedList<Arc>();
    this.grid = new Grid(inputGrid);
    this.startPuzzle = inputGrid;
    this.cellGrid = createCells();
    initQ();
    Boolean solvable = solveAC3();
    printSolution();

  }

  private void printSolution(){
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        int result = cellGrid[i][j].getDomainVal();
        System.out.print(result+ ", ");
      }
      System.out.println();
    }
  }

  private Boolean solveAC3(){
      while(!this.queueConstraints.isEmpty()){
        Arc current = queueConstraints.peek();
        if(current.revise()){
          this.queueConstraints = this.grid.constrainCol(current.getX().getColumn(), current.getX().getRow(), this.cellGrid, this.queueConstraints, false); //Assign arc constraints for all entries in that row
          this.queueConstraints = this.grid.constrainRow(current.getX().getColumn(), current.getX().getRow(), this.cellGrid, this.queueConstraints, false); //Assign arc constraints for all entries in that column
          this.queueConstraints = this.grid.constrainSquare(current.getX().getColumn(), current.getX().getRow(), this.cellGrid, this.queueConstraints, false); //Assign arc constraints for all entries in grid
        }else{
          //return false;
        }
        queueConstraints.remove();
      }
      return true;
  }

  private void initQ(){
    for (Integer i = 0; i<9; i++) {
      for (Integer j = 0; j<9; j++) {
          this.queueConstraints = this.grid.constrainCol(i, j, this.cellGrid, this.queueConstraints, true); //Assign arc constraints for all entries in that row
          this.queueConstraints = this.grid.constrainRow(i, j, this.cellGrid, this.queueConstraints, true); //Assign arc constraints for all entries in that column
          this.queueConstraints = this.grid.constrainSquare(i, j, this.cellGrid, this.queueConstraints, true); //Assign arc constraints for all entries in grid
      }
    }
  }


  private Cell[][] createCells(){
    Cell[][] result = new Cell[9][9];
    for(Integer i = 0; i < 9; i++){
      ArrayList<Integer> rowDomain = new ArrayList<Integer>();  //Start each row with a rowDomain 1 - 9
      ArrayList<Integer> colDomain = new ArrayList<Integer>();
      for (Integer j = 0; j< 9; j++){
        if(result[i][j] == null){
          result[i][j] = cellCreator(i,j);
        }
        if(result[j][i] == null){
          result[j][i] = cellCreator(j,i);
        }
        if(startPuzzle[i][j] != -1){
          rowDomain.add(startPuzzle[i][j]);
        }
        if(startPuzzle[j][i] != -1){
          colDomain.add(startPuzzle[j][i]);
        }

      }
      //printThisDomain(rowDomain);
      //System.out.println( " ughh" );
      result = this.grid.constrainDomain(rowDomain, result, i, true); //Constrain all entries in row
      result = this.grid.constrainDomain(colDomain, result, i, false); //Constrain all entries in column
    }
    result = this.grid.constrainDomainSquare(result);
    return result;
  }

  private Cell cellCreator(Integer row, Integer  column){
    Cell result;

    if(this.startPuzzle[row][column] != -1){
      ArrayList<Integer> domain = new ArrayList<Integer>();
      domain.add(this.startPuzzle[row][column]);
      result = new Cell(domain, row,  column);
    }else{
      ArrayList<Integer> baseDomain = new ArrayList<Integer>();
      baseDomain = fillDomain();
      result = new Cell(baseDomain,row, column);
    }
    return result;
  }

  public void printThisDomain(ArrayList<Integer> input){
    for (int i = 0; i < input.size(); i++) {
      System.out.print(input.get(i)+" and ");
    }
    System.out.println();
  }
  //For each cell's domain, we need to start at 1-9
  private ArrayList<Integer> fillDomain(){
    ArrayList<Integer> result = new ArrayList<Integer>();
    for(Integer i = 1; i < 10; i++){
      result.add(i);
    }
    return result;
  }
}
