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
      if((yDomain.size() == 1 && !yDomain.contains(xVal)) || (yDomain.size() > 1)){
        /*
        if(yDomain.contains(xVal)){
          yDomain.remove(yDomain.indexOf(xVal));
        }
        */

      }else{
        //this.x.printDomain();
        //System.out.println( " These two share " + this.x.getRow() + " row? " + this.y.getRow());
        //System.out.println( this.x.getRow() +" row, col: "  + this.x.getColumn() + " removing " + xVal );
        this.x.removeFromDomain(xVal);
        //this.x.printDomain();
        //System.out.println( "" + this.x);
        result = true;

      }
    }
    return result;
    /*
    * If the X domain has 1 value{
    *   if Y domain has 1 value:
    *     if Ydomain.value != xDomain.value: return true;
    *     else: return false;
    *   else:
    *     remove that value from Y;
    *     return true;

    if(xDomain.size() ==1){
      if(yDomain.size() ==1){
        //I dont think we should get to this test?
        if(yDomain.get(0) != xDomain.get(0)){
          return true;
        }else{
          return false;
        }
      }else{
        // We should have y become the x value in another arc and then propogate its own constraints
        //yDomain.remove(yDomain.indexOf(xDomain.get(0)));  //At this point do we need to create arcs for Y? since it changed?
        return true;
      }
    }else{
    */  /*
      * For each value in xDomain
      *   if xVal not in yDomain, set that to be the domain of X, create Arcs for X

      // If there exists a pair that satisfies the constraints...
      for(int i = 0; i < xDomain.size(); i++){
        int xVal = xDomain.get(i);
        if(!yDomain.contains(xVal)){
          x.setDomain(new ArrayList<Integer>(Arrays.asList(xVal)));
          return true;
        }else if(yDomain.size() > 1){
          yDomain.remove(yDomain.indexOf(xVal));
          return true;
        }
      }
    }
    return false;
    */
    /*
    if(xDomain.size() > 1){
      for (int i = 0; i < xDomain.size(); i++) {
        int xVal = xDomain.get(i);
      /*
      * If the X domain has more than 1 value, find a value not in Y domain and set that to be the domain of X
      * If the X domain has more than 1 value, and only shares values in Y domain, create an Arc
      */
      /*
      if(!yDomain.contains(xVal)){  //If the yDomain does not contain this value, we can use it
        ArrayList<Integer> newDomain = new ArrayList<Integer>();
        newDomain.add(xVal);
        this.x.setDomain(newDomain);
        System.out.println( " xVal is " + xVal );
        return true;
      }else{
        if(yDomain.size()>1){
          yDomain.remove(yDomain.indexOf(xVal));
          ArrayList<Integer> newDomain = new ArrayList<Integer>();
          newDomain.add(xVal);
          this.x.setDomain(newDomain);
          return true;
        }else{
          return false;
        }
      }
    }
    return false;
  }else{
    int xVal = xDomain.get(0);
    if(yDomain.size()>1 && yDomain.contains(xVal)){
      yDomain.remove(yDomain.indexOf(xVal));
      return true;
    }else{
      return false;
    }
  }
  */
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
