// imports ====================================================================
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;
// =============================================================================
// =============================================================================

// start of class HelpDisplay
// imports image and searches buffered images
public class HelpDisplay {
  public Rectangle backButton = new Rectangle(420, 500, 200, 100);

// start of method drawHelp ====================================================
// draws the help screen
  public void drawHelp(Graphics g) {
    BufferedImage img = null;
    try {
      img = ImageIO.read(new File("HelpInstructions.png"));
    }
    catch (IOException e) {
      System.out.println("Unable to find and display help instructions.");
      System.exit(0);
    }

    Graphics2D g2d = (Graphics2D) g;

    g.setColor(Arena.color);
    g.fillRect(0, 0, Arena.width, Arena.height);
    HISS.arena.drawItems(g);
    Font helpFont = new Font("arial", Font.BOLD, 50);
    g.setFont(helpFont);
    g.setColor(Snake.color);
    g.drawString("Help", 10, 40);
    g2d.drawImage(img, 10, 50, 1000, 500, null);

    Font buttonFont = new Font("arial", Font.BOLD, 20);
    g2d.draw(backButton);
    g.drawString("Back", backButton.x + 40, backButton.y + 65);
  } // end of drawHelp =========================================================


} // end of class hHlpDisplay
// =============================================================================
// =============================================================================
