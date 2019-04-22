// imports ==============================================================
import java.util.Random;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import java.lang.*;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;

//  ===========================================================================
//  ===========================================================================

public class HISS extends JPanel {

  public static final int FPS = 20;
  public static Arena arena;
  public char c;

  // the following variables hold different 'states' of the game so that the correct
  // draw method can be called accordingly
  public static State state = State.menu;
  public MenuDisplay menuDisplay = new MenuDisplay();
  public GameOverDisplay gameOverDisplay = new GameOverDisplay();
  public RunGameDisplay runGameDisplay = new RunGameDisplay();
  public PauseGameDisplay pauseGameDisplay = new PauseGameDisplay();
  public HelpDisplay helpDisplay = new HelpDisplay();
  //  ==========================================================================

  public HISS (){
    this.setPreferredSize(new Dimension(Arena.width, Arena.height));
    addMouseListener(new MouseInput());
    addKeyListener(new KeyboardInput());
    arena = new Arena();
    Thread mainThread = new Thread(new Runner());
    mainThread.start();
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
        }
        repaint();
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
      menuDisplay.drawMenu(g);
    }
    if (state == State.help) {
      helpDisplay.drawHelp(g);
    }
    if (state == State.runGame) {
      runGameDisplay.drawRunGame(g);
    }
    if (state == State.pauseGame) {
      pauseGameDisplay.drawPauseGame(g);
    }
    if (state == State.gameOver) {
      gameOverDisplay.drawGameOver(g);
    }
  }
}

interface Colorable {
  public void changeColor();
}
