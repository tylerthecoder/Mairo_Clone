public class Sprite {
  int x;
  int y;
  int w;
  int h;

  Sprite(int _x, int _y, int _w, int _h) {
    x = _x;
    y = _y;
    w = _w;
    h = _h;
  }

  Sprite(Json s) {
    x = (int) x.getLong("x");
    y = (int) x.getLong("y");
    w = (int) x.getLong("w");
    h = (int) x.getLong("h");
  }

  Json marshall() {
    JSON ob = JSON.newObject();
    ob.add("x", x);
    ob.add("y", y);
    ob.add("w", w);
    ob.add("h", h);
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

}