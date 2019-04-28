// imports ====================================================================
import java.awt.Graphics;

// ============================================================================
// ============================================================================

// start of class RunGameDisplay
// deals with drawing the current gameplay screen
public class RunGameDisplay {

// start of method drawRunGame ================================================
// draws the current game display to the screen
  public void drawRunGame(Graphics g) {
    g.setColor(Arena.color);
    g.fillRect(0, 0, Arena.width, Arena.height);
    HISS.arena.drawItems(g);
    HISS.arena.drawSnakes(g);
    HISS.arena.drawScore(g);
    HISS.arena.drawInedibleCount(g);
  } // end of drawRunGame =====================================================


} // end of class RunGameDisplay
// ============================================================================
// ===========================================================================
