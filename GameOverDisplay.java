import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Graphics2D;

public class GameOverDisplay {
  public Rectangle replayButton = new Rectangle(420, 400, 200, 100);

  public void drawGameOver(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;

    g.setColor(Color.WHITE);
    g.fillRect(0, 0, Arena.width, Arena.height);
    Font gameOverFont = new Font("monospaced", Font.BOLD, 50);
    g.setFont(gameOverFont);
    g.setColor(Color.BLACK);
    g.drawString("Game Over!", 380, 200);

    Font buttonFont = new Font("monospaced", Font.BOLD, 20);
    g2d.draw(replayButton);
    g.drawString("Replay", replayButton.x + 10, replayButton.y + 65);
    g.drawString("Score: " + Arena.score, 405, 330);
  }
}
