import java.awt.Graphics;

public abstract class Sprite {
  int x;
  int y;
  int prevX;
  int prevY;
  int w;
  int h;
  double vx;
  double vy;
  boolean isMario;

  Sprite() {
    x = 0;
    y = 0;
    w = 0;
    h = 0;
    vx = 0;
    vy = 0;
  }

  Sprite(int _x, int _y, int _w, int _h, double _vx, double _vy) {
    x = _x;
    y = _y;
    w = _w;
    h = _h;
  }

  void addPos(int[] vec) {
    int[] pos = {x , y};
    pos[0] += vec[0];
    pos[1] += vec[1];
    setPos(pos);
  }

  void setPos(int[] pos) {
    prevX = x;
    prevY = y;
    x = pos[0];
    y = pos[1];
  }

  void applyGravity() {
    vy += 1.8;
  }

  void addVel() {
    int[] vel = { (int)vx, (int)vy };
		addPos(vel);
  }

  public abstract void update(Model m);
  public abstract void draw(Graphics g, Model m);
  public abstract Json marshall();
  public abstract void spriteHit(Model m, Sprite s);

  boolean isColliding(Sprite s) {
    if (x + w > s.x       &&
				x     < s.x + s.w &&
				y + h > s.y           &&
				y     < s.y + s.h) {
          return true;
    }
    return false;
  }

  int getOut(Sprite s) { // return what direction you hit the brick
    if (y + h >= s.y && !(prevY + h > s.y)) { //from top
      y = s.y - h;
      return 1;
    } else if (y <= s.y + s.h && !(prevY < s.y + s.h)) { // from bottom
      y = s.y + s.h;
      return 3;
    } else if (x + w >= s.x && !(prevX + w > s.x)) { // from left
      x = s.x - w;
      return 4;
    } else if (x <= s.x + s.w && !(prevX < s.x + s.w)) { // from right
      x = s.x + s.w;
      return 2;
    }
    return 0;
  }
}