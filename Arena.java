import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.ArrayList;

public class Arena implements Colorable{
  public static final int width = 1025;
  public static final int height = 650;
  public static Color color = Color.WHITE;
  public static int score = 0;
  public ArrayList<Item> items;
  public ArrayList<Snake> snakes;

  public Arena() {
    items = createItems();
    snakes = createSnakes();
  }

  public void update(double time) {
    updateSnakes(time);
    changeColor();
  }

  public void updateSnakes(double time) {
    Pair v;
    for (Snake s: snakes){
      v = s.head.velocity;
      s.update(v, time);
      s.changeColor();
    }
  }


  @Override
  public void changeColor() {
    if (score > 50 & score <= 100) {
      color = Color.YELLOW;
    }
    if (score > 100) {
      color = Color.ORANGE;
    }
  }

  public ArrayList<Item> createItems() {
    ArrayList<Item> items = new ArrayList<Item>();
    boolean edible = true;
    for (int i=0; i<20; ++i){
      Item apple = new Item(edible);
      Item rock = new Item(!edible);
      items.add(apple);
      items.add(rock);
    }
    return items;
  }

  public void drawItems(Graphics g) {
    for(int i=0; i<items.size(); ++i) {
      items.get(i).drawItem(g);
    }
  }

  public ArrayList<Snake> createSnakes() {
    ArrayList<Snake> snakes = new ArrayList<Snake>(2);
    Snake s1 = new Snake(1, this);
    snakes.add(s1);
    Snake s2 = new Snake(2, this);
    snakes.add(s2);
    return snakes;
  }

  public void drawSnakes(Graphics g) {
    for (Snake s: snakes){
      s.drawSnake(g);
    }
  }

  public void drawScore(Graphics g) {
    String scoreString = "Score: " + score;
    g.drawString(scoreString, 850, 15);
  }

  public void drawInedibleCount(Graphics g) {
    String s1 = "Snake 1 Rocks Eaten: " + snakes.get(0).inedibleCount;
    g.drawString(s1, 850, 35);
    String s2 = "Snake 2 Rocks Eaten: " + snakes.get(1).inedibleCount;
    g.drawString(s2, 850, 55);
  }

}
