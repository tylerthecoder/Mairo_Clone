import java.awt.Graphics;
import java.awt.Image;


class CoinBlock extends Sprite {
  static Image[] images = {};
  int imgNum;
  int numCoinsSpit;

  CoinBlock (int _x, int _y) {
    super();
    x = _x;
    y = _y;
    w = 89;
    h = 83;
    loadImages();
  }

  CoinBlock (Json ob) {
    super();
    x = (int) ob.getLong("x");
    y = (int) ob.getLong("y");
    w = (int) ob.getLong("w");
    h = (int) ob.getLong("h");
    loadImages();
  }

  private void loadImages() {
    if (images.length == 0) {
      images = new Image[2];
      images[0] = View.loadImage("imgs/block1.png");
      images[1] = View.loadImage("imgs/block2.png");
    }
  }

  public Json marshall() {
    Json ob = Json.newObject();
		ob.add("x", x);
		ob.add("y", y);
		ob.add("w", w);
		ob.add("h", h);
		ob.add("type", "coinBlock");
		return ob;
  }

  public void update(Model m) {
  }

  public void draw (Graphics g, Model m) {
    imgNum = (numCoinsSpit >= 5) ? 1 : 0;
    g.drawImage(images[imgNum], x - m.camX, y - m.camY, null);
  }

  @Override
  public void spriteHit(Model m, Sprite s) {
    if (s.isMario && s.y > y+h-30 && numCoinsSpit < 5) { //if it is mario and he is below me
      Coin c = new Coin(this);
      m.addSprite(c);
      numCoinsSpit++;
    }
  }

}