public class Driver{
  public static void main(String[] args) {
    Integer [][] grid = new Integer[9][9];
    for(Integer i=0; i<9; i++){
      for(Integer j=0; j<9; j++){
        grid[i][j] = -1;
      }
    }

    grid[0][8] = 4;

    grid[1][0] = 2;
    grid[1][2] = 4;
    grid[1][4] = 3;
    grid[1][8] = 1;

    grid[2][0] = 8;
    grid[2][4] = 7;
    grid[2][5] = 1;
    grid[2][6] = 3;
    grid[2][8] = 2;

    grid[3][0] = 3;
    grid[3][3] = 8;
    grid[3][4] = 2;
    grid[3][6] = 4;
    grid[3][7] = 1;
    grid[3][8] = 9;

    grid[4][1] = 1;
    grid[4][3] = 7;
    grid[4][5] = 3;
    grid[4][7] = 2;

    grid[5][0] = 9;
    grid[5][1] = 8;
    grid[5][2] = 2;
    grid[5][4] = 4;
    grid[5][5] = 5;
    grid[5][8] = 6;

    grid[6][0] = 5;
    grid[6][2] = 3;
    grid[6][4] = 9;
    grid[6][5] = 6;
    grid[6][8] = 8;

    grid[7][0] = 1;
    grid[7][4] = 8;
    grid[7][6] = 2;
    grid[7][8] = 7;

    grid[8][0] = 7;

    Agent solver = new Agent(grid);

  }
}
