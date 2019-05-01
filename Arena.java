// imports =====================================================================
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.ArrayList;

// ===========================================================================
// ===========================================================================

// class Arena creates *two* instances of Snake and *twenty* instances of Item
// An instance of Arena contains the entire game
// Continually calling update() on an instance of arena causes the game to run

public class Arena implements Colorable{
  public static final int width = 1025;
  public static final int height = 650;
  public static Color color = Color.WHITE;
  public static int score = 0;
  public ArrayList<Item> items;
  public ArrayList<Snake> snakes;

//  start of constructor Arena =================================================
// creates instances of Snake and Item by calling on respective create methods
  public Arena() {
    items = createItems();
    snakes = createSnakes();
  } // end of constructor Arena ================================================


// start of method update ======================================================
// calls on updateSnakes to make the snake move and evolve as necessary
  public void update(double time) {
    updateSnakes(time);
    changeColor();
  } // end of update ===========================================================


// start of method updateSnakes, takes in a double variable ====================
// Loops through the ArrayList snakes and calls the method update in class Snake
// to update each snake
  private void updateSnakes(double time) {
    Pair v;
    for (Snake s: snakes){
      v = s.head.velocity;
      s.update(v, time);
      s.changeColor();
    }
  } // end of updateSnakes =====================================================

/* start of method changeColor =================================================
  overrides the abstract method from the
   interface Colorable. Causes the arena to change color once a certain score is
   reached
*/
  @Override
  public void changeColor() {
    if (score > 50 & score <= 100) {
      color = Color.YELLOW;
    }
    if (score > 100) {
      color = Color.ORANGE;
    }
  } // end of changeColor ======================================================


// start of method createItems =================================================
// returns an ArrayList of Items
// Creates 20 instances of items and adds them to an ArrayList
  public ArrayList<Item> createItems() {
    ArrayList<Item> items = new ArrayList<Item>();
    boolean edible = true;
    for (int i=0; i<20; ++i){
      Item apple = new Item(edible);
      Item rock = new Item(!edible);
      items.add(apple);
      items.add(rock);
    }
    return items;
  } // end of createItems =====================================================


// start of drawItems, takes in Graphics object and draws items to screen ======
  public void drawItems(Graphics g) {
    for(int i=0; i<items.size(); ++i) {
      items.get(i).drawItem(g);
    }
  } // end of drawItems ========================================================



// start of method createSnakes, returns an ArrayList of Snakes ===============
// creates two instances of Snake (Player 1 and Player 2) and adds them to an
// ArrayList
  public ArrayList<Snake> createSnakes() {
    ArrayList<Snake> snakes = new ArrayList<Snake>(2);
    Snake s1 = new Snake(1, this);
    snakes.add(s1);
    Snake s2 = new Snake(2, this);
    snakes.add(s2);
    return snakes;
  } // end of createSnakes ====================================================



// start of method drawSnakes =================================================
// takes in Graphic object and draws snakes to screen
  public void drawSnakes(Graphics g) {
    for (Snake s: snakes){
      s.drawSnake(g);
    }
  } // end of drawSnakes========================================================



// start of method drawScore ==================================================
// takes in Graphics object and draws current score
  public void drawScore(Graphics g) {
    String scoreString = "Score: " + score;
    g.drawString(scoreString, 850, 15);
  } // end of drawScore =======================================================



// start of method drawInedibleCount ===========================================
// takes in Graphics object and draws current count of inedible items eaten
  public void drawInedibleCount(Graphics g) {
    String s1 = "Snake 1 Rocks Eaten: " + snakes.get(0).inedibleCount;
    g.drawString(s1, 850, 35);
    String s2 = "Snake 2 Rocks Eaten: " + snakes.get(1).inedibleCount;
    g.drawString(s2, 850, 55);
  } // end of drawInedibleCount ================================================

} // end of class Arena

// ===========================================================================
// ===========================================================================
