// import sprites.*;

public abstract class Movable extends Sprite {
  int vx;
  int vy;

  Movable(int x, int y, int w, int h) {
    super(x,y,w,h);
  }

  Movable(int x, int y, int w, int h, int _vx, int _vy) {
    super(x,y,w,h);
    vx = _vx;
    vy = _vy;
  }

  Movable(Movable m) {
    super(m);

    vx = m.vx;
    vy = m.vy;
  }

  private void addPos (int vx, int vy) {
    prevX = x;
    prevY = y;
    x += vx;
    y += vy;
  }

  public void addVel() {
    addPos(vx, vy);
  }

  public void applyGravity() {
    vy += 1.8;
  }
}