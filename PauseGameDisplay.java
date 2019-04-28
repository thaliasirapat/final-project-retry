// imports  ====================================================================
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.Graphics2D;

//  ==========================================================================
//  ==========================================================================

// start of class PauseGameDisplay
// takes care of the display screen when user pauses the game

public class PauseGameDisplay{

  public Rectangle resumeButton = new Rectangle(420, 400, 200, 100);


// start of drawPauseGame ===================================================
// takes in Graphics object and draws the pause screen
  public void drawPauseGame(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;

    g.setColor(Arena.color);
    g.fillRect(0, 0, Arena.width, Arena.height);
    HISS.arena.drawItems(g);
    Font pauseFont = new Font("arial", Font.BOLD, 50);
    g.setFont(pauseFont);
    g.setColor(Snake.color);
    g.drawString("Game Paused", 350, 200);

    Font buttonFont = new Font("arial", Font.BOLD, 20);
    g2d.draw(resumeButton);
    g.drawString("Resume", resumeButton.x + 5, resumeButton.y + 65);
    g.drawString("Score: " + Arena.score, 420, 330);

  } // end of drawPauseGame ===================================================

} // end of class PauseGameDisplay

// ============================================================================
// ============================================================================
