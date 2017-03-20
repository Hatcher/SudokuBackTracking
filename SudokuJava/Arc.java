public class Arc{
  Cell x;
  Cell y;
  public Arc(Cell inputCellX, Cell inputCellY){
    this.x = inputCellX;
    this.y = inputCellY;
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
