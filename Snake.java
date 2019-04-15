import java.awt.Color;
import java.util.ArrayList;
import java.awt.Graphics;
import java.lang.*;


public class Snake implements Colorable {
  private int inedibleCount = 0;
  public ArrayList<Segment> body;
  public Segment head;
  private Color color = Color.GREEN;
  public int player;
  public Arena arena;
  private Snake friend;
  public int distanceBetweenSegments = 10;
  public int velocityComponent = 200;

  public Snake(int player, Arena arena) {
    this.player = player;
    this.arena = arena;
    Pair position;
    Pair velocity;
    if (player == 1) {
      position = new Pair(341,Arena.height);
      velocity = new Pair(0, -velocityComponent);
    }
    else {
      position = new Pair(682, Arena.height);
      velocity = new Pair (0, -velocityComponent);
    }

    // Creating head and body of snake
    head = new Segment(position, velocity);
    body = new ArrayList<Segment>();
    Segment s1 = new Segment(position.add(new Pair(0, distanceBetweenSegments)), velocity);
    body.add(s1);
    for (int i=0; i<8; ++i) {
      Segment s = new Segment(body.get(i).position.add(new Pair(0, distanceBetweenSegments)), velocity);
      body.add(s);
    }
  } // end of Snake constructor



  public void drawSnake(Graphics g){
    g.setColor(Color.GREEN);
    g.fillRect((int)head.position.x, (int)head.position.y, head.width, head.height);
    for (Segment s: body){
      g.fillRect((int)s.position.x, (int)s.position.y, s.width, s.height);
     }
  } // end of drawSnake



  // We're only moving the head, then the last segment in the body to replace the old position of the head
  public void update(Pair velocity, double time){
    Pair oldHeadPosition = head.position;
    head.position = head.position.add(velocity.times(time));
    int x = body.size()-1;
    body.remove(x);
    body.add(0, new Segment(oldHeadPosition, velocity));
   } // end of update


  public void changeVelocity(char c) {
    if (isMovingUp() || isMovingDown()) {
      if (c == 'a' || c == 'j') {
        head.velocity = new Pair(-velocityComponent, 0);
      }
      if (c == 'd' || c == 'l') {
        head.velocity = new Pair(velocityComponent, 0);
      }
    }
    if (isMovingRight() || isMovingLeft()) {
      if (c == 'w' || c == 'i') {
        head.velocity = new Pair(0, -velocityComponent);
      }
      if (c == 's' || c == 'k') {
        head.velocity = new Pair(0, velocityComponent);
      }
    }
  } // end of changeVelocity

  public boolean isMovingUp() {
    Pair upVelocity = new Pair(0, -velocityComponent);
    if (head.velocity.equalsTo(upVelocity)){
      return true;
    }
    return false;
  }

  public boolean isMovingDown() {
    Pair downVelocity = new Pair(0, velocityComponent);
    if (head.velocity.equalsTo(downVelocity)) {
      return true;
    }
    return false;
  }

  public boolean isMovingRight() {
    Pair rightVelocity = new Pair(velocityComponent, 0);
    if (head.velocity.equalsTo(rightVelocity)) {
      return true;
    }
    return false;
  }

  public boolean isMovingLeft() {
    Pair leftVelocity = new Pair(-velocityComponent, 0);
    if (head.velocity.equalsTo(leftVelocity)) {
      return true;
    }
    return false;
  }

  @Override
    public void changeColor() {
      if (arena.score > 5 && arena.score <= 10) {
        color = Color.PINK;
      }
      if (arena.score > 10 && arena.score <= 15) {
        color = Color.BLUE;
      }
      if (arena.score > 15 && arena.score <= 20) {
        color = Color.MAGENTA;
      }
    }




} // end of class Snake

class Segment {
  public Pair position;
  public Pair velocity;
  public int width = 10;
  public int height = 10;

  public Segment(Pair position, Pair velocity) {
    this.position = position;
    this.velocity = velocity;
  }
}
