import java.util.*;

public class Grid{
  private Integer[][] startPuzzle;
  public Grid(Integer[][] inputPuzzle){
    this.startPuzzle = inputPuzzle;
  }
  //Lets constrain the square
  public LinkedList<Arc> constrainSquare(int col, int row, Cell[][] grid, LinkedList<Arc> queueConstraints, boolean fill){
    int topLeftRow = Math.round(3*(row/3));
    int topLeftCol = Math.round(3*(col/3));
    for (int i = topLeftRow; i < topLeftRow+2; i++) {
      for (int j = topLeftCol; j < topLeftCol+2; j++) {
        if( i != row && j != col){ //If this is not the same row or column as the cell
          /*
          * We need to make arcs for the queue.
          */
          boolean arcExists = false;
          Arc newConstraint;
          if(fill){
            newConstraint = new Arc(grid[row][col], grid[i][j]);
          }else{
            newConstraint = new Arc(grid[i][j], grid[row][col]);
          }
          for (int q = 0; q < queueConstraints.size(); q++) {
            if(queueConstraints.get(q).equals(newConstraint)){
              arcExists = true;
            }
          }
          if(!arcExists){
            queueConstraints.add(newConstraint);
          }else{
            //System.out.println( "I am working" );
          }
        }
      }
    }
    return queueConstraints;
  }

  public LinkedList<Arc> constrainRow(int col, int row, Cell[][] grid, LinkedList<Arc> queueConstraints, boolean fill){
    for(int i = 0; i < 9; i++){
      if(i != col){
        boolean arcExists = false;
        Arc newConstraint;
        if(fill){
          newConstraint = new Arc(grid[row][col], grid[row][i]);
        }else{
          newConstraint = new Arc(grid[row][i], grid[row][col]);
        }

        for (int q = 0; q < queueConstraints.size(); q++) {
          if(queueConstraints.get(q).equals(newConstraint)){
            arcExists = true;
          }
        }

        if(!arcExists){
          queueConstraints.add(newConstraint);
        }
      }
    }
    return queueConstraints;
  }

  public LinkedList<Arc> constrainCol(int col, int row, Cell[][] grid, LinkedList<Arc> queueConstraints, boolean fill){
    //return this.constrainRow(row, col, grid, queueConstraints, fill);
    for(int i = 0; i < 9; i++){
      if(i != row){

        Arc newConstraint;
        if(fill){
          newConstraint = new Arc(grid[row][col], grid[i][col]);
        }else{
          newConstraint = new Arc(grid[i][col], grid[row][col]);
        }

        boolean arcExists = false;
        for (int q = 0; q < queueConstraints.size(); q++) {
          if(queueConstraints.get(q).equals(newConstraint)){
            arcExists = true;
          }
        }

        if(!arcExists){
          queueConstraints.add(newConstraint);
        }
      }
    }
    return queueConstraints;
  }

  public Cell[][] constrainDomain(ArrayList<Integer> inputDomain, Cell[][] inputPuzzle, int current, boolean row){
    for(int i = 0; i < 9; i++){
      if(row){
        if(this.startPuzzle[current][i] == -1){
          inputPuzzle[current][i].constrainDomain(inputDomain);
        }
      }else{
        if(this.startPuzzle[i][current] == -1){
          inputPuzzle[i][current].constrainDomain(inputDomain);
        }
      }
    }
    return inputPuzzle;
  }


   public Cell[][] constrainDomainSquare(Cell[][] inputPuzzle){
      ArrayList<Integer> resultDomain = new ArrayList<Integer>();
      for (int i = 0; i < 3; i++) {
        //System.out.println( " i is " + i );
        for(int j = 0; j < 9; j++){     //Iterate through the rows in this subsquare
          for (int t = 3*(i%3); t < 3*(i%3)+3; t++) { //Iterate through the columns in this subsquare
            if(this.startPuzzle[j][t] != -1){
              resultDomain.add(this.startPuzzle[j][t]);
            }
            //System.out.println( " j is " + j + " and t is " + t );
          }
          if(j % 3 == 2){
            for (int q = j-2; q < j+1; q++) {
              for (int t = 3*(i%3); t < 3*(i%3)+3; t++) {
                if(this.startPuzzle[q][t] == -1){
                  inputPuzzle[q][t].constrainDomain(resultDomain);
                  //System.out.println( "The domain for row: " +q + " and column: "+t+ " is ");
                  //inputPuzzle[q][t].printDomain();
                  //System.out.println( " " );
                }
              }
            }
            resultDomain = new ArrayList<Integer>();
          }
        }
      }

      return inputPuzzle;
    }

}
