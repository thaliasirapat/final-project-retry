import java.awt.Graphics;
public class RunGameDisplay {

  public void drawRunGame(Graphics g) {
    g.setColor(Arena.color);
    g.fillRect(0, 0, Arena.width, Arena.height);
    HISS.arena.drawItems(g);
    HISS.arena.drawSnakes(g);
    HISS.arena.drawScore(g);
    HISS.arena.drawInedibleCount(g);
  }
}
