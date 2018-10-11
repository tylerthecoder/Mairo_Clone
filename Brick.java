public class Brick {
	int x;
	int y;
	int w;
	int h;
	Brick(int X, int Y, int W, int H) {
		x=X;
		y=Y;
		w=W;
		h=H;	
	}	
	
	Brick(Json b) {
		x = (int) b.getLong("x");
		y = (int) b.getLong("y");
		w = (int) b.getLong("w");
		h = (int) b.getLong("h");
	}

	Json json() {
		Json ob = Json.newObject();
		ob.add("x", x);
		ob.add("y", y);
		ob.add("w", w);
		ob.add("h", h);
		return ob;
	}
}
