import java.awt.Color;
import java.awt.Graphics;
public class Brick {
	int x;
	int y;
	int w;
	int h;
  Color c;

	Brick(int X, int Y, int W, int H, Color C) {
		x=X;
		y=Y;
		w=W;
		h=H;	
    c=C;
	}	
	
	Brick(Json b) {
		x = (int) b.getLong("x");
		y = (int) b.getLong("y");
		w = (int) b.getLong("w");
		h = (int) b.getLong("h");
    int cr = (int) b.getLong("cr");
    int cg = (int) b.getLong("cg");
    int cb = (int) b.getLong("cb");
    c = new Color(cr, cg, cb);
	}

  public void draw(Graphics g, Model model) {
		g.setColor(c);
    g.fillRect(x - model.camX, y - model.camY, w, h);
  }

	Json json() {
		Json ob = Json.newObject();
		ob.add("x", x);
		ob.add("y", y);
		ob.add("w", w);
		ob.add("h", h);
    ob.add("cr", c.getRed());
    ob.add("cg", c.getGreen());
    ob.add("cb", c.getBlue());
		return ob;
	}
}
