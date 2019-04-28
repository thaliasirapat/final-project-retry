import java.awt.Color;
import java.util.ArrayList;
import java.awt.Graphics;
import java.lang.*;

public class MenuSnake {
  public int player;
  public ArrayList<Segment> body;
  public Segment head;
  public static Color color = Color.BLACK;
  public int velocityComponent = 200;

  public MenuSnake(int player) {
    this.player = player;
    Pair position;
    Pair velocity;
    if (player == 1) {
      position = new Pair(640,350);
      velocity = new Pair(0, -velocityComponent);
    }
    else {
      position = new Pair(640, 550);
      velocity = new Pair (0, -velocityComponent);
    }

    // Creating head and body of snake
    head = new Segment(position, velocity);
    body = new ArrayList<Segment>();
    Segment s1 = new Segment(position, velocity);
    body.add(s1);
    for (int i=0; i<8; ++i) {
      Segment s = new Segment(position, velocity);
      body.add(s);
    }
  }

  public void update(Pair velocity, double time){
    this.move(velocity, time);
    changeVelocity();

    Item item = this.eatenItem();
    if (item != null){
      this.evolve(item, velocity);
      item.eraseItem();
    }

  }
  public Item eatenItem(){
    for (Item i: HISS.arena.items){
      if(head.position.inRange(i.position, 10)){
        return i;
      }
    }
    return null;
  }

  public void evolve(Item item, Pair velocity){
    if(item.edible){
      Segment s = new Segment(body.get(0).position, velocity);
      body.add(s);
    }
  }
  public void changeVelocity() {
    if (player==1) {
      if (head.position.equalsTo(new Pair(640, 270))) {
        head.velocity = new Pair(-velocityComponent, 0);
      }
      if (head.position.equalsTo(new Pair(390, 270))) {
        head.velocity = new Pair(0, velocityComponent);
      }
      if (head.position.equalsTo(new Pair(390, 420))) {
        head.velocity = new Pair(velocityComponent, 0);
      }
      if (head.position.equalsTo(new Pair(640, 420))) {
        head.velocity = new Pair(0, -velocityComponent);
      }
    }
    if (player==2) {
      if (head.position.equalsTo(new Pair(640, 470))) {
        head.velocity = new Pair(-velocityComponent, 0);
      }
      if (head.position.equalsTo(new Pair(390, 470))) {
        head.velocity = new Pair(0, velocityComponent);
      }
      if (head.position.equalsTo(new Pair(390, 620))) {
        head.velocity = new Pair(velocityComponent, 0);
      }
      if (head.position.equalsTo(new Pair(640, 620))) {
        head.velocity = new Pair(0, -velocityComponent);
      }
    }
  }

  public void move(Pair velocity, double time) {
    Pair oldHeadPosition = head.position;
    head.position = head.position.add(velocity.times(time));
    int x = body.size()-1;
    body.remove(x);
    body.add(0, new Segment(oldHeadPosition, velocity));

  }
  public void drawSnake(Graphics g){
    g.setColor(color);
    g.fillRect((int)head.position.x, (int)head.position.y, head.width, head.height);
    for (Segment s: body){
      g.fillRect((int)s.position.x, (int)s.position.y, s.width, s.height);
     }
  }
}
