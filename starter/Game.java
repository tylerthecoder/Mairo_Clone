import javax.swing.JFrame;
import java.awt.Toolkit;

public class Game extends JFrame {
	Model model;
	Controller controller;
	View view;

	public Game() {
		model = new Model();
		controller = new Controller(model);
		view = new View(controller, model);
		this.setTitle("Turtle attack!");
		this.setSize(500, 500);
		this.setFocusable(true);
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.addMouseListener(controller);
		this.addKeyListener(controller);
	}

	public static void main(String[] args) {
		Game g = new Game();
		g.run();
	}

	public void run() {
		while (true) {
			controller.update();
			model.update();
			view.repaint();
			Toolkit.getDefaultToolkit().sync();

			try {
				Thread.sleep(40);
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
	}
}
