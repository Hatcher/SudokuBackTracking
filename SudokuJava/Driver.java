public class Driver{
  public static void main(String[] args) {
    Integer [][] grid = new Integer[9][9];
    for(Integer i=0; i<9; i++){
      for(Integer j=0; j<9; j++){
        grid[i][j] = -1;
      }
    }

    grid[0][2] = 3;
    grid[0][4] = 2;
    grid[0][6] = 6;

    grid[1][0] = 9;
    grid[1][3] = 3;
    grid[1][5] = 5;
    grid[1][8] = 1;

    grid[2][2] = 1;
    grid[2][3] = 8;
    grid[2][5] = 6;
    grid[2][6] = 4;

    grid[3][2] = 8;
    grid[3][3] = 1;
    grid[3][5] = 2;
    grid[3][6] = 9;

    grid[4][0] = 7;
    grid[4][8] = 8;

    grid[5][2] = 6;
    grid[5][3] = 7;
    grid[5][5] = 8;
    grid[5][6] = 2;

    grid[6][2] = 2;
    grid[6][3] = 6;
    grid[6][5] = 9;
    grid[6][6] = 5;

    grid[7][0] = 8;
    grid[7][3] = 2;
    grid[7][5] = 3;
    grid[7][8] = 9;

    grid[8][2] = 5;
    grid[8][4] = 1;
    grid[8][6] = 3;



    Agent solver = new Agent(grid);

  }
}
