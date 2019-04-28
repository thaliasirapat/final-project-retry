// imports ====================================================================
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.util.ArrayList;

// ============================================================================
// ============================================================================

public class MenuDisplay {
  public Rectangle playButton = new Rectangle(420, 300, 200, 100);
  public Rectangle helpButton = new Rectangle(420, 500, 200, 100);
  public ArrayList<MenuSnake> snakes = createSnakes();

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
  }

  public void update(double time) {
    updateSnakes(time);
  }

  public void updateSnakes(double time) {
    Pair v;
    for (MenuSnake s: snakes){
      v = s.head.velocity;
      s.update(v, time);
    }
  }

  public void drawSnakes(Graphics g) {
    for (MenuSnake s: snakes){
      s.drawSnake(g);
    }
  }

  public ArrayList<MenuSnake> createSnakes() {
    ArrayList<MenuSnake> snakes = new ArrayList<MenuSnake>(2);
    MenuSnake s1 = new MenuSnake(1);
    snakes.add(s1);
    MenuSnake s2 = new MenuSnake(2);
    snakes.add(s2);
    return snakes;
  }


}
