public class Pair{
  public double x;
  public double y;

  public Pair(double initX, double initY){
    x = initX;
    y = initY;
  }

  public Pair add(Pair toAdd){
    return new Pair(x + toAdd.x, y + toAdd.y);
  }

  public Pair divide(double denom){
    return new Pair(x / denom, y / denom);
  }

  public Pair times(double val){
    return new Pair(x * val, y * val);
  }

  public void flipX(){
    x = -x;
  }

  public void flipY(){
    y = -y;
  }
  public boolean equalsTo(Pair p) {
    if (p.x == this.x && p.y == this.y){
      return true;
    }
    return false;
  }

  public boolean isPositiveX() {
    if (this.x > 0) {
      return true;
    }
    return false;
  }

  public boolean isPositiveY() {
    if (this.y > 0) {
      return true;
    }
    return false;
  }

  public boolean inRange(Pair a, double range){
    if ((this.x >= a.x - range && this.x <= a.x + range) && (this.y >= a.y - range && this.y <= a.y + range)){
      return true;
    }
    return false;
  }

  public String toString(){
    return "x: " + x + "y: " + y;
  }
}
