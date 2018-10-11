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

	Mario() {
		super(150, 700, 60, 95, 0, 0);
		imgNumber = 0;
		moveSpeed = 8;
		flightTime = 0;
		isMario = true;
		stepSize = 2;
		loadImages();
	};

	private void loadImages () {
		if (images == null) {
			images = new Image[10];
			for (int i = 0; i <= 9; i++) {
				String imgSrc = "imgs/mario" + (i+1) + ".png";
				images[i] = View.loadImage(imgSrc);
			}
		}
	}

	public void update(Model m) {
		// apply gravity
		applyGravity();
		addVel();

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
			Sprite s = spriterator.next();
			if (isColliding(s)) {
				if (s.isMario)	continue;

				s.spriteHit(m, this);

				int dir = getOut(s);
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

	public void draw(Graphics g, Model m) {
		g.drawImage(images[imgNumber], x - m.camX, y - m.camY, null);
	}

	public void moveX(int xDir) {
		vx = moveSpeed * xDir;
	}

	public void jump() {
		if (flightTime <= 5) {
			vy += -6;
		}
	}

	public void crouch() {

	}

	@Override
	public void spriteHit(Model m, Sprite s) {}
}
