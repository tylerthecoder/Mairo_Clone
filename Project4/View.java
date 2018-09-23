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
    Image[] marioImages = new Image[10];
    for (int i = 0; i <= 9; i++) {
      String imgSrc = "imgs/mario" + (i+1) + ".png";
			marioImages[i] = loadImage(imgSrc);
    }
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

		// draw sprites
		for (int i = 0; i < model.sprites.size(); i++) {
			model.sprites.get(i).draw(g, model);
		}
	}
}
