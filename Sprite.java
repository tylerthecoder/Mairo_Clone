//package Paradigms.sprites;

import java.awt.Graphics;
// import Paradigms.models.*;

public abstract class Sprite {
  int x;
  int y;
  int prevX;
  int prevY;
  int w;
  int h;
  boolean isMario;

  Sprite() {
    x = 0;
    y = 0;
    w = 0;
    h = 0;
  }

  Sprite(int _x, int _y, int _w, int _h) {
    x = _x;
    y = _y;
    w = _w;
    h = _h;
  }

  Sprite(Sprite s) {
    x = s.x;
    y = s.y;
    w = s.w;
    h = s.h;
    prevX = s.prevX;
    prevY = s.prevY;
  }

  public abstract void update(Model m);
  public abstract void draw(Graphics g, Model m);
  public abstract Json marshall();
  public abstract void spriteHit(Model m, Sprite s);

  boolean isPointInsdie(int _x, int _y) {
    if (x + w > _x &&
				x     < _x &&
				y + h > _y &&
				y     < _y) {
          return true;
    }
    return false;
  }

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