import java.awt.Graphics;
import java.awt.Image;

class Coin extends Sprite {
  static Image image;

  Coin(CoinBlock cb) {
    super(0, 0, 75, 75, 0, 0);
    if (image == null) {
      image = View.loadImage("imgs/coin.png");
    }

    x = cb.x;
    y = cb.y - h;
    vx = 2 + 20 * (Math.random() - 0.5) * ((Math.random() > .5) ? -1:1);
    vy = -15 + (Math.random() - 0.5);
  }

  Coin(Coin c) {
    super(c);
  }

  @Override
  public Json marshall() {
    return null;
  }

  @Override
  public void update(Model m) {
    applyGravity();
    addVel();

    if (y > Game.windowHeight) {
      m.removeSprite(this);
    }
  }

  @Override
  public void draw(Graphics g, Model m) {
    g.drawImage(image, x - m.camX, y - m.camY, null);
  }

  @Override
  public void spriteHit(Model m, Sprite s) {

  }
}