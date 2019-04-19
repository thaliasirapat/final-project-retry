import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class MouseInput implements MouseListener {
  public void mousePressed(MouseEvent e){
    int mx = e.getX();
    int my = e.getY();

    if (HISS.state == State.menu) {
      //play button
      if (mx >= 420 && mx <= 620) {
        if (my >= 300 && my <= 400) {
          //Pressed play button
          HISS.state = State.runGame;
        }
      }
    }

    if (HISS.state == State.gameOver) {
      //replay button
      if (mx >= 420 && mx <= 620) {
        if (my >= 400 && my <= 500) {
          //Pressed replay button
          HISS.state = State.runGame;
          HISS.arena = new Arena();
          Arena.score = 0;
          Arena.color = Color.WHITE;
        }
      }
    }
  }
  public void mouseClicked(MouseEvent e){}
  public void mouseEntered(MouseEvent e){}
  public void mouseExited(MouseEvent e){}
  public void mouseReleased(MouseEvent e){}
}
