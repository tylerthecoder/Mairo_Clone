import java.awt.Color;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
// import Paradigms.Sprites.*;


public class Brick extends Sprite{
	Color c;
	boolean isFloor;
	static int floorHeight = 100;
	static BufferedImage groundImage;

	Brick(int X, int Y, int W, int H, Color _c) {
		super(X, Y, W, H);
		loadImage();
		c = _c;
	}

	Brick(int x, int w) { //this is the floor
		super(x, Game.windowHeight - floorHeight, w, floorHeight);
		c = Color.green;
		isFloor = true;
		loadImage();
	}

	Brick(Json b) {
		super();
		x = (int) b.getLong("x");
		y = (int) b.getLong("y");
		w = (int) b.getLong("w");
		h = (int) b.getLong("h");
		isFloor = (boolean) b.getBool("isFloor");
    int cr = (int) b.getLong("cr");
    int cg = (int) b.getLong("cg");
    int cb = (int) b.getLong("cb");
		c = new Color(cr, cg, cb);
		loadImage();
	}

	Brick(Brick b) {
		super(b);
		loadImage();
	}

	private void loadImage () {
		if (groundImage == null) {
			groundImage = (BufferedImage) View.loadImage("imgs/ground.png");
		}
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
		ob.add("isFloor", isFloor);
		ob.add("type", "brick");
    ob.add("cr", c.getRed());
    ob.add("cg", c.getGreen());
    ob.add("cb", c.getBlue());
		return ob;
	}

  public void draw(Graphics g, Model model) {
		if (isFloor) {
			// g.setColor(Color.GREEN);
			// g.fillRect(x - model.camX, y, w, h);
			// g.drawImage(groundImage, x, y, (x+w) - model.camX, (y+h), 0, 0, 600, 480, Color.black, null);
			int imageWidth = groundImage.getWidth(null);
			int imageHeight = groundImage.getHeight(null);
			double scaledImgWidth = ((double) imageWidth / (double) imageHeight) * floorHeight;
			int numOfImages = w / (int) scaledImgWidth;
			for (int i = 0; i < numOfImages; i++) {
				g.drawImage(groundImage,
					(int) (x + i * scaledImgWidth) - model.camX,
					y,
					(int) (x + (i+1) * (scaledImgWidth)) - model.camX,
					y+h,
					0, 0, imageWidth, imageHeight, Color.black, null
				);
			}



			//draw the excess
			int leftOverWidth = w - (int) (scaledImgWidth * numOfImages);
			if (leftOverWidth != 0) {
				BufferedImage img = groundImage.getSubimage(0, 0, (int) (leftOverWidth * (imageWidth / scaledImgWidth)), groundImage.getHeight());
				int startX = (int)(x + numOfImages * scaledImgWidth);
				g.drawImage(img,
					startX - model.camX,
					y,
					startX + (int)scaledImgWidth - model.camX,
					y+h,
					0, 0, imageWidth, imageHeight, Color.black, null
				);
			}

		} else {
			g.setColor(c);
    	g.fillRect(x - model.camX, y - model.camY, w, h);
		}
	}

	@Override
	public void spriteHit(Model m, Sprite s) {}
}
