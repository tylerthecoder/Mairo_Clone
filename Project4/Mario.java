import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Image;
import java.awt.Graphics;

public class Mario extends Sprite {
	int imgNumber;
	int moveSpeed;
	int flightTime;
	int stepSize;
	int sinceStep;
  static Image[] images;

	double vy;
  double vx;

	Mario() {
		super(150, 700, 60, 95);
		imgNumber = 0;
		moveSpeed = 8;
		flightTime = 0;
		isMario = true;
		stepSize = 2;
    images = View.loadMarioImages();
	};

	public void update(Model m) {
		// apply gravity
		vy += 1.8;
		int[] vel = { (int)vx, (int)vy };
		addPos(vel);

		// control picture
		if (vx != 0) {
			sinceStep++;
			if (sinceStep >= stepSize) {
				imgNumber = (imgNumber+1)%5;
				sinceStep = 0;
			}
			if (vx > 0) {
				imgNumber = imgNumber%5;
			} else if (vx < 0) {
				imgNumber = (imgNumber%5) + 5;
			}
		}

		// control jumping
		flightTime++;

		// check for collision
		Iterator<Sprite> spriterator = m.sprites.iterator();
		while(spriterator.hasNext()) {
			Sprite b = spriterator.next();
			if (isColliding(b)) {
				if (b.isMario)	continue;

				int dir = getOut(b);
				if (dir == 1 ) { //on ground
					flightTime = 0;
					vy = 0;
				} else if (dir == 2) {
					vx = 0;
				} else if (dir == 3) {
					vy = 0;
				} else if (dir == 4) {
					vx = 0;
				}
			}
		}
	}

	public Json marshall () {
		Json ob = Json.newObject();
		ob.add("x", x);
		ob.add("y", y);
		ob.add("w", w);
		ob.add("h", h);
		ob.add("type", "mario");
		return ob;
	}

	public void unmarshall () {

	}

	public void draw(Graphics g, Model m) {
		g.drawImage(images[imgNumber], x - m.camX, y - m.camY, null);
	}

	public void moveX(int xDir) {
		vx = moveSpeed * xDir;
	}


	public void jump() {
		if (flightTime <= 5) {
			vy += -5;
		}
	}

	public void crouch() {

	}
}
