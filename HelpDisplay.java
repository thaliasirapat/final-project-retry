import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Graphics2D;

public class HelpDisplay { //import image search buffered image
  public Rectangle backButton = new Rectangle(420, 500, 200, 100);

  public void drawHelp(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;

    g.setColor(Arena.color);
    g.fillRect(0, 0, Arena.width, Arena.height);
    Font helpFont = new Font("monospaced", Font.BOLD, 50);
    g.setFont(helpFont);
    g.setColor(Snake.color);
    g.drawString("Help", 10, 40);

    Font buttonFont = new Font("monospaced", Font.BOLD, 20);
    g2d.draw(backButton);
    g.drawString("Back", backButton.x + 35, backButton.y + 65);
  }
}
