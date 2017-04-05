import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Reader{
  private Integer[][] grid;
  public Reader(String filePath, String delimeter){
    BufferedReader br = null;
    String line;
    grid = new Integer[9][9];
    try{
      br = new BufferedReader(new FileReader(filePath));
      int rowCount = 0;
      while ((line = br.readLine()) != null){  //While the buffer still has a line
        String[] values = line.split(delimeter);
        for(int i = 0; i < values.length; i++){
          grid[rowCount][i] = Integer.parseInt(values[i]);
        }
        rowCount++;
      }
    } catch (FileNotFoundException e){
      e.printStackTrace();
    } catch (IOException e){
      e.printStackTrace();
    } finally {
      if (br != null){
        try{
          br.close();
        } catch (IOException e){
          e.printStackTrace();
        }
      }
    }
  }

  public Integer[][] getGrid(){
    return this.grid;
  }

}
