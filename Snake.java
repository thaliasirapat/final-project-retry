// imports
import java.awt.Color;
import java.util.ArrayList;
import java.awt.Graphics;
import java.lang.*;

//  ===========================================================================
// ============================================================================

// Start of class Snake
// Contains rules of snake movement and evolution

public class Snake implements Colorable {
  public int inedibleCount = 0;
  private ArrayList<Segment> body;
  public Segment head;
  public static Color color = Color.BLACK;
  public int player;
  public Arena arena;
  public int velocityComponent = 200;


// constructor for class snake =========================================
// Takes in an Arena instance and a player number
// Sets position according to player number and creates the snake's body

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
    Segment s1 = new Segment(position, velocity);
    body.add(s1);
    for (int i=0; i<8; ++i) {
      Segment s = new Segment(position, velocity);
      body.add(s);
    }
  } // end of Snake constructor ================================================



// start of method drawSnake ==============================================
// called in class Arena
  public void drawSnake(Graphics g){
    g.setColor(color);
    g.fillRect((int)head.position.x, (int)head.position.y, head.width, head.height);
    for (Segment s: body){
      g.fillRect((int)s.position.x, (int)s.position.y, s.width, s.height);
     }
  } // end of drawSnake  =====================================================



// start of method move =====================================================
// controls how the snake moves on the screen
// the head moves according to physics, but the rest of the body moves by moving
// the last segment to replace the old position of the head

  private void move(Pair velocity, double time) {
    Pair oldHeadPosition = head.position;
    head.position = head.position.add(velocity.times(time));
    int x = body.size()-1;
    body.remove(x);
    body.add(0, new Segment(oldHeadPosition, velocity));

  } // end of move  ============================================================



  // start of method update  =====================================================
  // Calls on method move, and checks if the snake is eating itself, its friend,
  // or running into the wall. If the snake eats an item, it calls on evolve()
  // to change the snake accordingly
  public void update(Pair velocity, double time){
    this.move(velocity, time);

    if (eatSelf()){
      HISS.state = State.gameOver;
    }
    if (eatFriend()){
      HISS.state = State.gameOver;
    }
    if (hitWall()){
      HISS.state = State.gameOver;
    }
    Item item = this.eatenItem();
    if (item != null){
      this.evolve(item, velocity);
      item.eraseItem();
    }
    if (this.inedibleCount == 5){
      HISS.state = State.gameOver;
    }
  } // end of update  ========================================================



// start of method eatSelf  =====================================================
// checks if the snake is eating itslef
   public boolean eatSelf(){
     for (Segment s: this.body){
       if (head.position.equalsTo(s.position))
        return true;
     }
     return false;
   } // end of eatSelf  ========================================================



// start of method eatFriend  ==================================================
// checks if the snake is eating its friend
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
   } // end of eatFriend  =====================================================



// start of method hitWall  =====================================================
// checks if the snake is running into the wall
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
   } // end of hitWall  ========================================================




// start of method changeVelocity  =============================================
// deals with changing the velocity of the snake based on the character input from
// the keyboard
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
  } // end of changeVelocity  ==================================================



// start of method eatenItem  ==================================================
// returns an item if it is eaten, null if not
  public Item eatenItem(){
    for (Item i: arena.items){
      if(head.position.inRange(i.position, 10)){
        return i;
      }
    }
    return null;
  } // end of hasEatenItem  ====================================================



//start of method evolve  =====================================================
// calls on eatenItem() and increases length of snake and score if edible item is eaten
// increases inedibleCount when inedible item is eaten
  public void evolve(Item item, Pair velocity){
    if(item.edible){
      arena.score++;
      Segment s = new Segment(body.get(0).position, velocity);
      body.add(s);
    }
    else{
      inedibleCount++;
    }
  } // end of evolve  =========================================================



// The following methods check the direction that the snake is moving in ======
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

// end of methods checking direction of snake movement =========================

// start of method changeColor  ================================================
// overrides the abstract method from Colorable interface
// changes color of the snake based on the score
  @Override
    public void changeColor() {
      if (arena.score > 50 && arena.score <= 100) {
        color = Color.MAGENTA;
      }
      if (arena.score > 100) {
        color = Color.BLUE;
      }
    } // end of changeColor  ===================================================



} // end of class Snake

 //=============================================================================
 //=============================================================================


// start of class Segment
// the body of the snake is an ArrayList of Segments
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
// end of class Segment
//  ============================================================================
// =============================================================================
