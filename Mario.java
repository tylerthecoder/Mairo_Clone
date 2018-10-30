// package Paradigms.sprites;

import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Image;
import java.awt.Graphics;
// import Paradigms.sprites.*;

public class Mario extends Movable {
	final int moveSpeed = 8;
	final int stepSize = 2;
  static Image[] images;
	int imgNumber = 0;
	int flightTime = 0;
	int sinceStep;
	int jumpCount = 0;
	int coinBlockCount = 0;
	int coinsCollected = 0;
	int waitTime = 0;
	boolean dead = false;

	Mario(Json m) {
		this((int) m.getLong("x"), (int) m.getLong("y"));
	}

	Mario(int x, int y) {
		super(x, y, 60, 95);
		isMario = true;
		loadImages();
	}

	Mario(Mario m) {
		super(m);
		imgNumber = m.imgNumber;
		flightTime = m.flightTime;
		coinBlockCount = m.coinBlockCount;
		coinsCollected = m.coinsCollected;
		dead = m.dead;
		isMario = true;
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

	private void loadImages () {
		if (images == null) {
			images = new Image[10];
			for (int i = 0; i <= 9; i++) {
				String imgSrc = "imgs/mario" + (i+1) + ".png";
				images[i] = View.loadImage(imgSrc);
			}
		}
	}

	@Override
	public void update(Model m) {
		// apply gravity
		applyGravity();
		addVel();

		// controll death
		if (y > Game.windowHeight) {
			dead = true;
		}

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
		for(Sprite s : m.sprites) {
			if (s.isMario) continue;
			if (isColliding(s)) {

				int dir = getOut(s);

				s.spriteHit(m, this);

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

	public void draw(Graphics g, Model m) {
		g.drawImage(images[imgNumber], x - m.camX, y - m.camY, null);
	}

	public void moveX(int xDir) {
		vx = moveSpeed * xDir;
	}

	public void jump() {
		if (flightTime == 0) {
			jumpCount++;
		}
		if (flightTime <= 5) {
			vy += -6;
		}
	}

	public void waitPlz() {
		// do nothing
		waitTime++;
	}

	@Override
	public void spriteHit(Model m, Sprite s) {}
}
