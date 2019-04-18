import java.util.Random;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import java.lang.*;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;

public class HISS extends JPanel implements KeyListener {

  public static final int FPS = 20;
  public Arena arena;
  public char c;
  public Menu menu = new Menu();
  public static State state = State.menu;

  public HISS (){
    arena = new Arena();
    this.setPreferredSize(new Dimension(arena.width, arena.height));
    if (state == State.menu) {
      addMouseListener(new MouseInput());
    }
    addKeyListener(this);
    Thread mainThread = new Thread(new Runner());
    mainThread.start();
  }

  @Override
  public void keyPressed(KeyEvent e) {
    char c = e.getKeyChar();
    if (state == State.runGame) {
      int x = getPlayer(c);
      Snake snake;
      if ( x == 1) {
        snake = arena.snakes.get(0);
      }
      else {
        snake = arena.snakes.get(1);
      }
      snake.changeVelocity(c);
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
  public void keyReleased(KeyEvent e) {
     char c = e.getKeyChar();
   }

  @Override
  public void keyTyped(KeyEvent e) {
    char c = e.getKeyChar();
  }

  public void addNotify() {
      super.addNotify();
      requestFocus();
  }

  class Runner implements Runnable {
    public void run() {
      while (true) {
        if (state == State.runGame) {
          arena.update((double)1/FPS);
          repaint();
        }
        try{
          Thread.sleep(1000/FPS);
        }
        catch(InterruptedException e){
        }
      }
    }
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("HISS: Two Player Snake!");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setContentPane(new HISS());
    frame.pack();
    frame.setVisible(true);
  }

  @Override
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    if (state == State.menu) {
      menu.drawMenu(g);
    }
    else if (state == State.runGame) {
      g.setColor(arena.color);
      g.fillRect(0, 0, arena.width, arena.height);
      arena.drawItems(g);
      arena.drawSnakes(g);
      arena.drawScore(g);
      arena.drawInedibleCount(g);
    }
  }
}

class Pair{
  public double x;
  public double y;

  public Pair(double initX, double initY){
    x = initX;
    y = initY;
  }

  public Pair add(Pair toAdd){
    return new Pair(x + toAdd.x, y + toAdd.y);
  }

  public Pair divide(double denom){
    return new Pair(x / denom, y / denom);
  }

  public Pair times(double val){
    return new Pair(x * val, y * val);
  }

  public void flipX(){
    x = -x;
  }

  public void flipY(){
    y = -y;
  }
  public boolean equalsTo(Pair p) {
    if (p.x == this.x && p.y == this.y){
      return true;
    }
    return false;
  }

  public boolean isPositiveX() {
    if (this.x > 0) {
      return true;
    }
    return false;
  }

  public boolean isPositiveY() {
    if (this.y > 0) {
      return true;
    }
    return false;
  }

  public boolean inRange(Pair a, double range){
    if ((this.x >= a.x - range && this.x <= a.x + range) && (this.y >= a.y - range && this.y <= a.y + range)){
      return true;
    }
    return false;
  }

  public String toString(){
    return "x: " + x + "y: " + y;
  }
}

interface Colorable {
  public void changeColor();
}
