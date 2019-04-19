import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Graphics2D;

public class MenuDisplay {
  public Rectangle playButton = new Rectangle(420, 300, 200, 100);
  public Rectangle helpButton = new Rectangle(420, 500, 200, 100);

  public void drawMenu(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;

    g.setColor(Color.WHITE);
    g.fillRect(0, 0, Arena.width, Arena.height);
    Font titleFont = new Font("monospaced", Font.BOLD, 50);
    g.setFont(titleFont);
    g.setColor(Color.BLACK);
    g.drawString("HISS: a two player snake game", 80, 200);

    Font buttonFont = new Font("monospaced", Font.BOLD, 20);
    g2d.draw(playButton);
    g.drawString("Play", playButton.x + 35, playButton.y + 65);
    g2d.draw(helpButton);
    g.drawString("Help", helpButton.x + 35, helpButton.y + 65);
  }
}
