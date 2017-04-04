import java.util.ArrayList;

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
    for (int i = 0; i < xDomain.size(); i++) {
      int xVal = xDomain.get(i);
      if(!yDomain.contains(xVal)){  //If the yDomain does not contain this value, we can use it
        ArrayList<Integer> newDomain = new ArrayList<Integer>();
        newDomain.add(xVal);
        this.x.setDomain(newDomain);
        System.out.println( " xVal is " + xVal );
        return true;
      }else{
        //xDomain.remove(xDomain.indexOf(xVal));
      }
    }
    return false;
  }

  public void delete(){

  }


  public boolean equals(Arc test){
      //if x = test.x
        //if y = test.y
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
