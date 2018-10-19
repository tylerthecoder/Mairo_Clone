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
	Editor editor;
  Image backgroundImage;

	View(Model m){
		model = m;
    backgroundImage = loadImage("imgs/background.png");
	}

	View(Model m, Editor e) {
		model = m;
		editor = e;
    backgroundImage = loadImage("imgs/background.png");
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

    // draw background
    g.drawImage(backgroundImage, -model.camX / 3, -model.camY / 3, null);

		// draw sprites
		for (int i = 0; i < model.sprites.size(); i++) {
			model.sprites.get(i).draw(g, model);
		}

		// if there is an editor, draw it
		if (editor != null) {
			editor.draw(g);
		}
	}

}
