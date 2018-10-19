import java.awt.Color;
import java.awt.Graphics;


public class Brick extends Sprite{
  Color c;

	Brick(int X, int Y, int W, int H, Color _c) {
		super(X, Y, W, H, 0, 0);
		c = _c;
	}

	Brick(Json b) {
		super(0,0,0,0,0,0);
		x = (int) b.getLong("x");
		y = (int) b.getLong("y");
		w = (int) b.getLong("w");
		h = (int) b.getLong("h");
    int cr = (int) b.getLong("cr");
    int cg = (int) b.getLong("cg");
    int cb = (int) b.getLong("cb");
    c = new Color(cr, cg, cb);
	}

	Brick(Brick b) {
		super(b);
		c = b.c;
	}

	public void setColor(Color _c) {
		c = _c;
	}

	public void update(Model m) {

	}

	public Json marshall() {
		Json ob = Json.newObject();
		ob.add("x", x);
		ob.add("y", y);
		ob.add("w", w);
		ob.add("h", h);
		ob.add("type", "brick");
    ob.add("cr", c.getRed());
    ob.add("cg", c.getGreen());
    ob.add("cb", c.getBlue());
		return ob;
	}

  public void draw(Graphics g, Model model) {
		g.setColor(c);
    g.fillRect(x - model.camX, y - model.camY, w, h);
	}

	@Override
	public void spriteHit(Model m, Sprite s) {}
}
