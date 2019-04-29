// imports ====================================================================
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Graphics2D;

// ============================================================================
// ============================================================================


// start of class GameOverDisplay ==============================================
// handles the state of GameOver and contains methods that draws the game over
// display to the screen
public class GameOverDisplay {

  public Rectangle replayButton = new Rectangle(300, 500, 200, 100);
  public Rectangle menuButton = new Rectangle(550, 500, 200, 100);


// start of method drawGameOver ===============================================
// draws the game over screen
  public void drawGameOver(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;

    g.setColor(Arena.color);
    g.fillRect(0, 0, Arena.width, Arena.height);
    HISS.arena.drawItems(g);
    Font gameOverFont = new Font("monospaced", Font.BOLD, 50);
    g.setFont(gameOverFont);
    g.setColor(Snake.color);
    g.drawString("Game Over!", 380, 200);
    g.drawString("Score: " + Arena.score, 400, 330);

    g2d.draw(replayButton);
    g.drawString("Replay", replayButton.x + 10, replayButton.y + 65);
    g2d.draw(menuButton);
    g.drawString("Menu", menuButton.x + 40, replayButton.y + 65);
  }

} // end of GameOverDisplay
// =============================================================================
// =============================================================================
