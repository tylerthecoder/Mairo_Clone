import java.awt.Graphics;

public abstract class Sprite {
  int x;
  int y;
  int prevX;
  int prevY;
  int w;
  int h;
  boolean isMario;

  Sprite(int _x, int _y, int _w, int _h) {
    x = _x;
    y = _y;
    w = _w;
    h = _h;
  }

  int[] getPos() {
    int[] pos = {x, y};
    return pos;
  }

  int[] getDim() {
    int[] dim = {w, h};
    return dim;
  }

  void addPos(int[] vec) {
    int[] pos = getPos();
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

  public abstract void update(Model m);
  public abstract void draw(Graphics g, Model m);
  public abstract Json marshall();
  public abstract void unmarshall();

  boolean isColliding(Sprite s) {
    int[] sDim = s.getDim();
    int[] sPos = s.getPos();
    if (x + w > sPos[0]       &&
				x     < sPos[0] + sDim[0] &&
				y + h > sPos[1]           &&
				y     < sPos[1] + sDim[1]) {
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