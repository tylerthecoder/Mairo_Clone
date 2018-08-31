import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import javax.swing.JButton;
import java.awt.Color;

class View extends JPanel {
	Model model;
	Image[] marioImages;

	View(Controller c, Model m){
		model = m;
		c.setView(this);
		marioImages = new Image[5];
		marioImages[0] = loadImage("imgs/mario1.png");
		marioImages[1] = loadImage("imgs/mario2.png");
		marioImages[2] = loadImage("imgs/mario3.png");
		marioImages[3] = loadImage("imgs/mario4.png");
		marioImages[4] = loadImage("imgs/mario5.png");
	}

	private Image loadImage(String src) {
		try {
			return ImageIO.read(new File(src)); 	
		} catch (IOException e) {
			return null;
		}
	}

	public void paintComponent(Graphics g) {
		// clear screen
		g.setColor(new Color(255, 255, 255));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		// paint bricks
		g.setColor(new Color(0, 0, 0));
		for (int i = 0; i < model.bricks.size(); i++) {
			Brick b = model.bricks.get(i);
			g.drawRect(b.x - model.camX, b.y - model.camY, b.w, b.h);
		}

		//draw ground
		g.setColor(Color.gray);
		g.drawLine(0, 596, 2000, 596);

		//draw Mario
		Mario mario = model.mario;
		g.drawImage(marioImages[mario.imgNumber], mario.x - model.camX, mario.y - model.camY, null);
	}
}
