import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class MouseInput implements MouseListener {
  public void mouseClicked(MouseEvent e) {
    int mx = e.getX();
    int my = e.getY();
  }
  public void mouseEntered(MouseEvent e) {
    int mx = e.getX();
    int my = e.getY();
  }
  public void mouseExited(MouseEvent e){
    int mx = e.getX();
    int my = e.getY();
  }
  public void mousePressed(MouseEvent e){
    int mx = e.getX();
    int my = e.getY();

    //play button
    //public Rectangle playButton = new Rectangle(420, 300, 200, 100);
    if (mx >= 420 && mx <= 620) {
      if (my >= 300 && my <= 400) {
        //Pressed play button
        HISS.state = State.runGame;
      }
    }
  }
  public void mouseReleased(MouseEvent e){
    int mx = e.getX();
    int my = e.getY();
  }
}
