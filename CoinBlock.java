import java.awt.Graphics;
import java.awt.Image;
// import sprites.*;


class CoinBlock extends Sprite {
  static Image[] images = {};
  int imgNum;
  int numCoinsSpit;
  int cooldown;

  CoinBlock (int x, int y) {
    super(x, y, 89, 83);
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

  CoinBlock (CoinBlock cb) {
    super(cb);
    imgNum = cb.imgNum;
    numCoinsSpit = cb.numCoinsSpit;
    cooldown = cb.cooldown;
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
    if (cooldown > 0) {
      cooldown--;
    }
  }

  public void draw (Graphics g, Model m) {
    imgNum = (numCoinsSpit >= 5) ? 1 : 0;
    g.drawImage(images[imgNum], x - m.camX, y - m.camY, null);
  }

  @Override
  public void spriteHit(Model m, Sprite s) {
    if (s.isMario && s.y > y+h-30 && numCoinsSpit < 5 && cooldown <= 0) { //if it is mario and he is below me
      Coin c = new Coin(this);
      System.out.println("Hit");
      m.mario.coins++;
      m.addSprite(c);
      cooldown = 12;
      numCoinsSpit++;
    }
  }

}