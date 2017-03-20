import java.util.ArrayList;
import java.util.*;

public class Agent{
  /*
  * The sudoku problem is a csp that is solvable using an inference-csp
  */

  /*
  * Create an agent who stores our input grid.
  */
  private Integer[][] startPuzzle;
  private Cell[][] cellGrid;
  private Queue<Arc> queueConstraints; //Has queue for storing the pairs of constrained cells
  public Agent(Integer[][] inputGrid){
    this.startPuzzle = inputGrid;
    this.cellGrid = createCells();

    //Create pairs of constraints
    initQ();

    //Solve sudoku puzzle using AC3 algorithm
    Boolean solvable = solveAC3();
  }

  private Boolean solveAC3(){
      while(!this.queueConstraints.isEmpty()){
        Arc current = queueConstraints.remove();
        if(revise(current) == true){
          Cell currentX = current.getX();
          ArrayList<Integer> currentXDomain = currentX.getDomain();
          if(currentXDomain.size() == 0){
            return false;
          }
        }
      }
      return true;
  }

  private Boolean revise(Arc inputArc){
    Boolean revised = false;
    Cell currentX = inputArc.getX();
    ArrayList<Integer> xDomain = currentX.getDomain();

    System.out.println(xDomain.size());
    return true;
  }

  private void initQ(){
    //For each entry in cellGrid we want to create an arc of the entry and its constraining neighbor
    for (Integer i = 0; i<9; i++) {
      System.out.println("Here we are");
      //Assign arc constraints for all entries in that row
      //Assign arc constraints for all entries in that column
      for (Integer j = 0; j<8; j++) {
        System.out.println("Here we go");
        for (Integer t = j+1; t<8; t++) {
            System.out.println("Here we go");
            Arc tempRow = new Arc(this.cellGrid[i][j],this.cellGrid[i][t]);   // All entries in

            Arc tempCol = new Arc(this.cellGrid[j][i],this.cellGrid[t][i]);  // All entries in column
            System.out.println("Here we dont");
            queueConstraints.add(tempRow);
            queueConstraints.add(tempCol);
            System.out.println("Here we are");
            //Assign arc constraints for all entries in grid
            if(i%3 == 0){
              System.out.println("Start");
              if(j%3 == 0){
                Arc temp11 = new Arc(this.cellGrid[i][j], this.cellGrid[i+1][j+1]);
                Arc temp12 = new Arc(this.cellGrid[i][j], this.cellGrid[i+1][j+2]);
                Arc temp21 = new Arc(this.cellGrid[i][j], this.cellGrid[i+2][j+1]);
                Arc temp22 = new Arc(this.cellGrid[i][j], this.cellGrid[i+2][j+2]);
                queueConstraints.add(temp11);
                queueConstraints.add(temp12);
                queueConstraints.add(temp21);
                queueConstraints.add(temp22);
              }else if(j%3 == 1){
                Arc temp10 = new Arc(this.cellGrid[i][j], this.cellGrid[i+1][j-1]);
                Arc temp12 = new Arc(this.cellGrid[i][j], this.cellGrid[i+1][j+1]);
                Arc temp20 = new Arc(this.cellGrid[i][j], this.cellGrid[i+2][j-1]);
                Arc temp22 = new Arc(this.cellGrid[i][j], this.cellGrid[i+2][j+1]);
                queueConstraints.add(temp10);
                queueConstraints.add(temp12);
                queueConstraints.add(temp20);
                queueConstraints.add(temp22);
              }else{
                /*
                Arc temp10 = new Arc(this.cellGrid[i][j], this.cellGrid[i+1][j-2]);
                Arc temp11 = new Arc(this.cellGrid[i][j], this.cellGrid[i+1][j-1]);
                Arc temp20 = new Arc(this.cellGrid[i][j], this.cellGrid[i+2][j-2]);
                Arc temp21 = new Arc(this.cellGrid[i][j], this.cellGrid[i+2][j-1]);
                queueConstraints.add(temp10);
                queueConstraints.add(temp11);
                queueConstraints.add(temp20);
                queueConstraints.add(temp21);
                */
              }
            }else if(i%3 == 1){
              if(j%3 == 0){
                Arc temp21 = new Arc(this.cellGrid[i][j], this.cellGrid[i+1][j+1]);
                Arc temp22 = new Arc(this.cellGrid[i][j], this.cellGrid[i+1][j+2]);
                queueConstraints.add(temp21);
                queueConstraints.add(temp22);
              }else if(j%3 == 1){
                Arc temp20 = new Arc(this.cellGrid[i][j], this.cellGrid[i+1][j-1]);
                Arc temp22 = new Arc(this.cellGrid[i][j], this.cellGrid[i+1][j+1]);
                queueConstraints.add(temp20);
                queueConstraints.add(temp22);
              }else{
                /*
                Arc temp20 = new Arc(this.cellGrid[i][j], this.cellGrid[i+1][j-1]);
                Arc temp21 = new Arc(this.cellGrid[i][j], this.cellGrid[i+1][j-2]);
                queueConstraints.add(temp20);
                queueConstraints.add(temp21);
                */
              }
            }
          }
        }
      }
    }

  private Cell[][] createCells(){
    ArrayList<Integer> domain = fillDomain();
    Cell[][] result = new Cell[9][9];

    for(Integer i = 0; i < 9; i++){
      for (Integer j = 0; j< 9; j++){
        if(this.startPuzzle[i][j] == -1){
          //The value of [i][j] == -1 then we can say that the domain is 1-9
          Cell temp = new Cell(domain,i,j);
          result[i][j] = temp;
        }else{
          //Else the domain is this.startPuzzle[i][j]
          ArrayList<Integer> cellDomain = new ArrayList<Integer>();
          cellDomain.add(this.startPuzzle[i][j]);
          Cell temp = new Cell(cellDomain, i, j);
          result[i][j] = temp;
        }
      }
    }

    return result;
  }


  //For each cell's domain, we need to start at 1-9
  private ArrayList<Integer> fillDomain(){
    ArrayList<Integer> result = new ArrayList<Integer>();
    for(Integer i = 0; i < 9; i++){
      result.add(i);
    }
    return result;
  }
}
