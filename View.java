import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
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
	}
}
