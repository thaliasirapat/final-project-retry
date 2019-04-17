import java.awt.Color;
import java.util.ArrayList;
import java.awt.Graphics;
import java.lang.*;


public class Snake implements Colorable {
  public int inedibleCount = 0;
  public ArrayList<Segment> body;
  public Segment head;
  private Color color = Color.BLACK;
  public int player;
  public Arena arena;
  public int distanceBetweenSegments = 0;
  public int velocityComponent = 200; // for debugging let's make it slower for now

  public Snake(int player, Arena arena) {
    this.player = player;
    this.arena = arena;
    Pair position;
    Pair velocity;
    if (player == 1) {
      position = new Pair(341,Arena.height+1);
      velocity = new Pair(0, -velocityComponent);
    }
    else {
      position = new Pair(682, Arena.height+1);
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
    g.setColor(color);
    g.fillRect((int)head.position.x, (int)head.position.y, head.width, head.height);
    for (Segment s: body){
      g.fillRect((int)s.position.x, (int)s.position.y, s.width, s.height);
     }
  } // end of drawSnake

  public void move(Pair velocity, double time) {
    Pair oldHeadPosition = head.position;
    head.position = head.position.add(velocity.times(time));
    int x = body.size()-1;
    body.remove(x);
    body.add(0, new Segment(oldHeadPosition, velocity));

  }

  // We're only moving the head, then the last segment in the body to replace the old position of the head
  public void update(Pair velocity, double time){
    this.move(velocity, time);

    if (eatSelf()){
      System.out.println("Game Over!");
      System.out.println("Your score is: " + arena.score);
      System.out.println("Eat self");
      System.exit(0);
    }
    if (eatFriend()){
      System.out.println("Game Over!");
      System.out.println("Your score is: " + arena.score);
      System.out.println("Eat friend");
      System.exit(0);
    }
    if (hitWall()){
      System.out.println("Game Over!");
      System.out.println("Your score is: " + arena.score);
      System.out.println("Hit wall");
      System.exit(0);
    }
    Item item = this.eatenItem();
    if (item != null){
      this.evolve(item, velocity);
      item.eraseItem();
    }
    if (this.inedibleCount == 5){
      System.out.println("Game over!");
      System.out.println("Your score is: " + arena.score);
      System.out.println("Ate 5 inedible foods");
      System.exit(0);
    }
   } // end of update

   public boolean eatSelf(){
     for (Segment s: this.body){
       if (head.position.equalsTo(s.position))
        return true;
     }
     return false;
   } // end of eatSelf

   public boolean eatFriend(){
     Snake friend;
     if (player == 1) {
       friend = arena.snakes.get(1);
     }
     else {
       friend = arena.snakes.get(0);
     }

     for (Segment s: friend.body){
       if (this.head.position.inRange(s.position, 1))
        return true;
     }
     return false;
   } // end of eatFriend

   public boolean hitWall(){
     double x = this.head.position.x;
     double y = this.head.position.y;
     boolean hitWall = false;
     if (x > arena. width || x < 0 ){
       hitWall = true;
     }
     else if ( y > arena.height || y < 0){
       hitWall = true;
     }
     return hitWall;
   } // end of hitWall


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


  public Item eatenItem(){
    for (Item i: arena.items){
      if(head.position.inRange(i.position, 10)){
        return i;
      }
    }
    return null;
  } // end of hasEatenItem



  public void evolve(Item item, Pair velocity){
    if(item.edible){
      arena.score++;
      for (int i=0; i<1; ++i) {
        Segment s = new Segment(body.get(i).position.add(new Pair(0, distanceBetweenSegments)), velocity);
        body.add(s);
      }
    }
    else{
      inedibleCount++;
    }
  } // end of evolve



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
      if (arena.score > 50 && arena.score <= 100) {
        color = Color.MAGENTA;
      }
      if (arena.score > 100) {
        color = Color.BLUE;
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
