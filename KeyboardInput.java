import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class KeyboardInput implements KeyListener {
  @Override
  public void keyPressed(KeyEvent e) {
    char c = e.getKeyChar();
    if (HISS.state == State.runGame) {
      int x = getPlayer(c);
      Snake snake;
      if ( x == 1) {
        snake = HISS.arena.snakes.get(0);
      }
      else {
        snake = HISS.arena.snakes.get(1);
      }
      snake.changeVelocity(c);

      if (c=='p') {
        HISS.state = State.pauseGame;
      }
    }

  }

  public int getPlayer(char c){
    if ( c == 'w' || c == 's' || c == 'a' || c == 'd'){
      return 1;
    }
    else if (c == 'i' || c == 'k' ||c == 'j' ||c == 'l' ){
      return 2;
    }
    else{
      return 3;
    }
  }

  @Override
  public void keyReleased(KeyEvent e){}

  @Override
  public void keyTyped(KeyEvent e){}
}
