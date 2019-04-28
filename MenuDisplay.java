// imports ====================================================================
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.util.ArrayList;

// ============================================================================
// ============================================================================

//start of class MenuDisplay
// This class contains the draw method (and other necessary methods) for the start
// screen of the game

public class MenuDisplay {
  public Rectangle playButton = new Rectangle(420, 300, 200, 100);
  public Rectangle helpButton = new Rectangle(420, 500, 200, 100);
  public ArrayList<MenuSnake> snakes = createSnakes();


// start of method drawMenu ===================================================
// takes in Graphics object and draws the menu screen when called on
  public void drawMenu(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    g.setColor(Arena.color);
    g.fillRect(0, 0, Arena.width, Arena.height);
    HISS.arena.drawItems(g);
    Font titleFont = new Font("arial", Font.BOLD, 50);
    g.setFont(titleFont);
    g.setColor(Snake.color);
    g.drawString("HISS: Two Player Snake!", 225, 200);

    Font buttonFont = new Font("arial", Font.BOLD, 20);
    g2d.draw(playButton);
    g.drawString("Play", playButton.x + 45, playButton.y + 65);
    g2d.draw(helpButton);
    g.drawString("Help", helpButton.x + 45, helpButton.y + 65);

    drawSnakes(g);
  } // end of drawMenu  ========================================================


// start of method update  =====================================================
// makes the snakes move on the menu
  public void update(double time) {
    updateSnakes(time);
  } // end of update  =========================================================


// start of updateSnakes  =====================================================
// helper method of method update
  public void updateSnakes(double time) {
    Pair v;
    for (MenuSnake s: snakes){
      v = s.head.velocity;
      s.update(v, time);
    }
  } // end of updateSnakes  ===================================================


// start of drawSnakes  =======================================================
// draws Snakes to screen
  public void drawSnakes(Graphics g) {
    for (MenuSnake s: snakes){
      s.drawSnake(g);
    }
  } // end of drawSnakes  ======================================================



// start of createSnakes  ======================================================
// returns an ArrayList of snakes
  public ArrayList<MenuSnake> createSnakes() {
    ArrayList<MenuSnake> snakes = new ArrayList<MenuSnake>(2);
    MenuSnake s1 = new MenuSnake(1);
    snakes.add(s1);
    MenuSnake s2 = new MenuSnake(2);
    snakes.add(s2);
    return snakes;
  } // end of createSnakes  ====================================================


} // end of class MenuDisplay

//  ============================================================================
//  ============================================================================
