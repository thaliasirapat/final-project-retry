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
// class HISS is the main class of the program and the entry point into the game
// HISS creates an instance of Arena, implements multi-threading, and calls the
// appropriate draw menus depending on the current state of the game

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


  // start of HISS constructor =================================================
  // adds MouseListener and KeyboardListener, creates an instance of arena and
  // starts the main thread
  public HISS (){
    this.setPreferredSize(new Dimension(Arena.width, Arena.height));
    addMouseListener(new MouseInput());
    addKeyListener(new KeyboardInput());
    arena = new Arena();
    Thread mainThread = new Thread(new Runner());
    mainThread.start();
  } // end of HISS constructor =================================================

// start of method addNotify ===================================================
// inherits from the parent method in JFrame
  public void addNotify() {
      super.addNotify();
      requestFocus();
  } // end of addNotify ========================================================



// start of class Runner =======================================================
// enables multi-threading and runs different versions of the code depending
// on the state of the game
  class Runner implements Runnable {
    public void run() {
      while (true) {
        if (state == State.menu) {
          menuDisplay.update((double)1/FPS);
        }
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
  } // end of class Runner =====================================================


// start of main method =======================================================
// Instantiates JFrame
  public static void main(String[] args) {
    JFrame frame = new JFrame("HISS: Two Player Snake!");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setContentPane(new HISS());
    frame.pack();
    frame.setVisible(true);
  } // end of main method ======================================================



// start of paintComponent method ==============================================
// handles all the graphics of the game depending on the state
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
  } // end of paintComponent ==================================================

}
// end of class HISS
// ===========================================================================
// ===========================================================================

// start of interface Colorable ================================================
// contains method header changeColor, applied to both snakes and arena
interface Colorable {
  public void changeColor();
}// end of Colorable

// =============================================================================
// =============================================================================
