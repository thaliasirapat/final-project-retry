// imports ====================================================================
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Color;

// ============================================================================
// ============================================================================


// start of class MouseInput ===================================================
// handles user input via the trackpad / mouse in the starting and end game
// screens

public class MouseInput implements MouseListener {

  // start of method mousePressed ==============================================
  // handles input from the mouse / trackpad
  public void mousePressed(MouseEvent e){
    int mx = e.getX();
    int my = e.getY();

    if (HISS.state == State.menu) {
      // Play button
      if (mx >= 420 && mx <= 620) {
        if (my >= 300 && my <= 400) {
          //Pressed play button
          HISS.state = State.runGame;
          HISS.arena = new Arena();
          Arena.score = 0;
          Arena.color = Color.WHITE;
          Snake.color = Color.BLACK;
        }
      }
      // help button
      if (mx >= 420 && mx <= 620) {
        if (my >= 500 && my <= 600) {
          //Pressed help button
          HISS.state = State.help;
        }
      }
    }

    else if (HISS.state == State.help) {
      //back button
      if (mx >= 420 && mx <= 620) {
        if (my >= 500 && my <= 600) {
          HISS.state = State.menu;
        }
      }
    }

    else if (HISS.state == State.pauseGame) {
      //resume button
      if (mx >= 420 && mx <= 620) {
        if (my >= 400 && my <= 500) {
          //Pressed resume button
          HISS.state = State.runGame;
        }
      }
    }

    else if (HISS.state == State.gameOver) {
      //replay button
      if (mx >= 300 && mx <= 500) {
        if (my >= 500 && my <= 600) {
          //Pressed replay button
          HISS.state = State.runGame;
          HISS.arena = new Arena();
          Arena.score = 0;
          Arena.color = Color.WHITE;
          Snake.color = Color.BLACK;
        }
      }
      //menu button
      if (mx >= 550 && mx <= 750) {
        if (my >= 500 && my <= 600) {
          HISS.state = State.menu;
        }
      }
    }
  } // end of mousePressed =====================================================

  // inherited methods =========================================================
  public void mouseClicked(MouseEvent e){}
  public void mouseEntered(MouseEvent e){}
  public void mouseExited(MouseEvent e){}
  public void mouseReleased(MouseEvent e){}

} // end of class MouseInput ===================================================

// =============================================================================
// =============================================================================
