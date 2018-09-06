import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Image;

public class Mario {
	int x;
	int y;
	int w;
	int h;
	int imgNumber;
	int moveSpeed;
	int flightTime;
  static Image[] images;  

	double vy;
	
	Mario() {
		imgNumber = 0;
		x = 50;
		y = 400;
		w = 60;
		h = 95;
		moveSpeed = 8;
		flightTime = 0;
    images = View.loadMarioImages();
	}

	public void update(Model m) {
		// apply gravity
		vy += 1.8;
		y += vy;
		
		// check for collision
		int[] hitData = isHittingBricks(m.bricks);	
		int hitDir = hitData[0];

		if (hitDir == 4 || hitDir == 2) {
			y += hitData[1];
      // only stop his velocity if he is hitting a brick
      if (hitDir == 4) {
        vy = 0;
        flightTime = 0;
      }
		} else {
			flightTime++;
		}
	}

	public int moveX(ArrayList<Brick> bricks, int xDir) {
		int[] hitData = isHittingBricks(bricks);	
		int hitDir = hitData[0];
		x += moveSpeed * xDir;
		// we are not hititng a block
		if (hitDir == 0) {
			imgNumber = (imgNumber+1)%5;
			return moveSpeed * xDir;
		} else {
			int dx = hitData[1];
			x += dx; 
			return 0; 
		}
	}


	public void jump() {
		// if you are moving up and are below a certian height then you can jump higher 
		if (flightTime <= 5) {
			vy += -5;
		}
	}

	public void crouch() {

	}

	private int[] isHittingBricks(ArrayList<Brick> bricks) {
		// returns direction in which the brick is being hit
		// 0 not hit, 1 left side of brick, 2 bottom of brick, 3 right of brick, 4 top of brick
		// second parameter is the amount to move mario to be flush with object
    Iterator<Brick> brickIterator = bricks.iterator();
    while (brickIterator.hasNext()) {
			Brick brick = brickIterator.next(); 
			if (x + w > brick.x           &&
					x     < brick.x + brick.w &&
					y + h > brick.y           &&
					y     < brick.y + brick.h) {
				int dx = ((brick.x + brick.w/2) - (x + w/2)) / (brick.w / 2);
				int dy = ((brick.y + brick.h/2) - (y + h/2)) / (brick.h / 2);
				if (Math.abs(dx) > Math.abs(dy)) { //we want to leave out of the x direction
					if (x < brick.x) {
						return new int[] {1, (brick.x - w) - x}; 
					} else {
						return new int[] {3, (brick.x + brick.w) - x};
					}
				} else {
					if (y > brick.y) {
						return new int[] {2, (brick.y + brick.h) - y};
					} else {
						return new int[] {4, (brick.y - h) - y};
					}
				}
			}

    }
		return new int[] {0,0};
	}
}
