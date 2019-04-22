import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.Graphics2D;

public class PauseGameDisplay{

  public Rectangle resumeButton = new Rectangle(420, 400, 200, 100);

  public void drawPauseGame(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;

    g.setColor(Arena.color);
    g.fillRect(0, 0, Arena.width, Arena.height);
    Font pauseFont = new Font("arial", Font.BOLD, 50);
    g.setFont(pauseFont);
    g.setColor(Snake.color);
    g.drawString("Game Paused", 350, 200);

    Font buttonFont = new Font("arial", Font.BOLD, 20);
    g2d.draw(resumeButton);
    g.drawString("Resume", resumeButton.x + 10, resumeButton.y + 65);
    g.drawString("Score: " + Arena.score, 400, 330);

  }
}
