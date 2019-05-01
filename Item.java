// imports
import java.awt.Image;
import java.util.Random;
import java.awt.Color;
import java.awt.Graphics;

// ===========================================================================
// ===========================================================================

//start of class Item
// defines items and methods for drawing and erasing items
public class Item {

  public Pair position;
  public final int width = 10;
  public final int height = 10;
  public boolean edible;
  private Random rand = new Random();

  // constructor for Item ====================================================
  // takes in a boolean value for edible/inedible and randomizes position of item
  public Item(boolean edible) {
    this.position = new Pair((double)rand.nextInt(Arena.width - width), (double)rand.nextInt(Arena.height - height));
    this.edible = edible;
  } // end of constructor  ====================================================


// start of method drawItem
// draws items to the screen
  public void drawItem(Graphics g) {
    if (edible == true) {
      g.setColor(Color.RED); //red for apple
    }
    else {
      g.setColor(Color.GRAY); //gray for rocks
    }
    g.fillRect((int)position.x, (int)position.y, this.width, this.height);
  } // end of drawItem

// start of eraseItem
// called when an item is eaten; moves the eaten item to another random position on the screen
  public void eraseItem() {
    this.position = new Pair((double)rand.nextInt(Arena.width - width), (double)rand.nextInt(Arena.height - height));
  } // end of eraseItem


} // end of class Item

// =============================================================================
// =============================================================================
