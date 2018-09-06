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
  Image backgroundImage;

	View(Controller c, Model m){
		model = m;
		c.setView(this);
    backgroundImage = loadImage("imgs/background.png");
	}

  public static Image[] loadMarioImages () {
    Image[] marioImages = new Image[5];
    for (int i = 0; i < 5; i++) {
      String imgSrc = "imgs/mario" + (i+1) + ".png";
      marioImages[i] = loadImage(imgSrc); 
    }
    System.out.println(marioImages);
    return marioImages;
  }

	static Image loadImage(String src) {
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

    //draw background
    g.drawImage(backgroundImage, -model.camX / 3, -model.camY / 3, null);

		// paint bricks
		g.setColor(new Color(255, 255, 255));
		for (int i = 0; i < model.bricks.size(); i++) {
			Brick b = model.bricks.get(i);
			g.fillRect(b.x - model.camX, b.y - model.camY, b.w, b.h);
		}

		//draw ground
		g.setColor(Color.gray);
		g.drawLine(0, 596, 2000, 596);

		//draw Mario
		Mario mario = model.mario;
		g.drawImage(mario.images[mario.imgNumber], mario.x - model.camX, mario.y - model.camY, null);
	}
}
