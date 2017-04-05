import java.util.ArrayList;
import java.util.Arrays;

public class Arc{
  Cell x;
  Cell y;
  public Arc(Cell inputCellX, Cell inputCellY){
    this.x = inputCellX;
    this.y = inputCellY;
  }

  public Boolean revise(){
    ArrayList<Integer> xDomain = this.x.getDomain();
    ArrayList<Integer> yDomain = this.y.getDomain();

    /*
    * result = false;
    * For each value in xDomain,
    *   if xDomain.value is in the yDomain,
    *     remove from xDomain(value)
    *     result = true;
    *   endif
    * endfor
    * return result;
    */
    boolean result = false;
    for(int  i = 0; i < xDomain.size(); i++){
      int xVal = xDomain.get(i);
      //if yDomain contains anything other than xVal, we can satisfy the constraint and return false, otherwise remove xVal and return true;
      //if yDomain !contain xVal && yDomain > 0 or yDomain contains xVal && yDomain > 1
      if(!((yDomain.size() == 1 && !yDomain.contains(xVal)) || (yDomain.size() > 1))){
        this.x.removeFromDomain(xVal);
        result = true;
      }
    }
    return result;
  }

  public boolean equals(Arc test){
      if(this.x.equals(test.getX())){
        if(this.y.equals(test.getY())){
          return true;
        }
      }
      return false;
  }

  private void setX(Cell inputX){
   this.x = inputX;
  }

  public Cell getX(){
   return this.x;
  }

  private void setY(Cell inputY){
   this.y = inputY;
  }

  public Cell getY(){
   return this.y;
  }


}
