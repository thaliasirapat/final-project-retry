/* class Pair is used to hold positions and velocities of Items and Segments
   Each Pair has an x and a y value which represents the x and y coordinate for
   position / x and y component for velocity


   class Pair has helper methods that help add, times, compare Pairs or determine
   if a Pair is within a given range
*/

public class Pair{
  public double x;
  public double y;

  public Pair(double initX, double initY){
    x = initX;
    y = initY;
  }

// the following methods help perform arithmetic operations on Pairs & the names
// are self-explanatory ========================================================
  public Pair add(Pair toAdd){
    return new Pair(x + toAdd.x, y + toAdd.y);
  }

  public Pair divide(double denom){
    return new Pair(x / denom, y / denom);
  }

  public Pair times(double val){
    return new Pair(x * val, y * val);
  }

// =============================================================================

// start of method equalsTo ===================================================
// compares two pairs. if the x and y values are both equal method returns true
  public boolean equalsTo(Pair p) {
    if (p.x == this.x && p.y == this.y){
      return true;
    }
    return false;
  } // end of equalsTo ========================================================


// start of inRange ============================================================
// takes in a Pair and a range. if the two pairs being compared are within the
// specified range of each other then method returns true
  public boolean inRange(Pair a, double range){
    if ((this.x >= a.x - range && this.x <= a.x + range) && (this.y >= a.y - range && this.y <= a.y + range)){
      return true;
    }
    return false;
  } // end of inRange ==========================================================

}
// end of class Pair
// ============================================================================
// ============================================================================
