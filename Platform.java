import java.awt.Graphics;
import java.awt.Image;


public class Platform extends Sprite {
  int startX;
  int endX;
  int dir;
  static Image image;


  Platform() {
    super(100,100,414,122,0,0);
    startX = 100;
    endX = 300;
    x = startX;
    dir = 1;
    loadImage();
  }

  Platform(Json ob) {
    super(0,0,0,0,0,0);
    startX = (int) ob.getLong("sx");
    endX = (int) ob.getLong("ex");
    x = startX;
    y = (int) ob.getLong("y");
    w = (int) ob.getLong("w");
    h = (int) ob.getLong("h");
  }

  private void loadImage () {
    if (image == null) {
      image = View.loadImage("imgs/platform.jpeg");
    }
  }

  @Override
  public Json marshall() {
    Json ob = Json.newObject();
		ob.add("y", y);
		ob.add("w", w);
    ob.add("h", h);
    ob.add("sx", startX);
    ob.add("ex", endX);
		ob.add("type", "platform");
		return ob;
  }

  @Override
  public void update(Model m) {
    x += dir;
    if (x > endX) {
      dir = -1;
    }
    if (x < startX) {
      dir = 1;
    }
  }

  @Override
  public void draw(Graphics g, Model m) {
    g.drawImage(image, x - m.camX, y - m.camY, 200, 40, null);
  }

  @Override
  public void spriteHit(Model m, Sprite s) {

  }
}