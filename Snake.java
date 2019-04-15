import java.awt.Color;
import java.util.ArrayList;
import java.awt.Graphics;
import java.lang.*;


public class Snake implements Colorable {
  public int length = 3;
  private int inedibleCount = 0;
  public ArrayList<Segment> body;
  private Color color = Color.GREEN;
  public int player; // we should have player 1 and 2 so the snake responds to different keys
  public Arena arena; // i think we need to have this so that we can use arena as an argument
  private Snake friend;
  public Pair newVelocity;

  public Snake(int player, Arena arena) {
    this.player = player;
    this.arena = arena;
    Pair newVelocity = new Pair(0, -40);
    Pair position;
    if (player == 1) {
      position = new Pair(341,384);
    }
    else {
      position = new Pair(682,384);
    }
    body = new ArrayList<Segment>(3);
    Segment s1 = new Segment(position, newVelocity);
    body.add(s1);
    Segment s2 = new Segment(s1.position.add(new Pair(0,20)), newVelocity);
    body.add(s2);
    Segment s3 = new Segment(s2.position.add(new Pair(0,20)), newVelocity);
    body.add(s3);
  }

  public void drawSnake(Graphics g){
    g.setColor(Color.GREEN);
    for (Segment s: body){
      g.fillRect((int)s.position.x, (int)s.position.y, s.width, s.height);
     }
  }

  public void update() {
    for (int i = body.size() - 1; i > 0 ; --i){
      body.set(i, body.get(i-1));
    }
    Pair newPosition = body.get(1).position.add(newVelocity);
    body.set(0, new Segment (newPosition, newVelocity));


  }

  public void changeNewVelocity(char c) {
    if (player == 1) {
      if (isMovingUp() || isMovingDown()) {
        if (c == 'a') {
          newVelocity = new Pair(-40, 0);
        }
        if (c == 'd') {
          newVelocity = new Pair(40, 0);

        }
      }
      if (isMovingRight() || isMovingLeft()) {
        if (c == 'w') {
          newVelocity = new Pair(0, -40);
        }
        if (c == 's') {
          newVelocity = new Pair(0, 40);
        }
      }
    }
    if (player == 2) {
      if (isMovingUp() || isMovingDown()) {
        if (c == 'j') {
          newVelocity = new Pair(-40, 0);
        }
        if (c == 'l') {
          newVelocity = new Pair(40, 0);
        }
      }
      if (isMovingRight() || isMovingLeft()) {
        if (c == 'i') {
          newVelocity = new Pair(0, -40);
        }
        if (c == 'k') {
          newVelocity = new Pair(0, 40);
        }
      }
    }
  }



  public boolean isMovingUp() {
    Pair upVelocity = new Pair(0, -40);
    if (body.get(0).velocity.equalsTo(upVelocity)){
      return true;
    }
    return false;
  }

  public boolean isMovingDown() {
    Pair downVelocity = new Pair(0, 40);
    if (body.get(0).velocity.equalsTo(downVelocity)) {
      return true;
    }
    return false;
  }

  public boolean isMovingRight() {
    Pair rightVelocity = new Pair(40, 0);
    if (body.get(0).velocity.equalsTo(rightVelocity)) {
      return true;
    }
    return false;
  }

  public boolean isMovingLeft() {
    Pair leftVelocity = new Pair(-40, 0);
    if (body.get(0).velocity.equalsTo(leftVelocity)) {
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

}

class Segment {
  public Pair position;
  public Pair velocity;
  public int width = 20;
  public int height = 20;

  public Segment(Pair position, Pair velocity) {
    this.position = position;
    this.velocity = velocity;
  }
}
