import javax.swing.JFrame;
import java.awt.Toolkit;

public class MapEditor extends JFrame {
  Model model;
	EditorController controller;
  View view;
  Editor editor;
  static int windowHeight;

  public MapEditor () {
    model = new Model();
    editor = new Editor(model);
		controller = new EditorController(model, editor);
		view = new View(controller, model);
		windowHeight = 1000;
		this.setTitle("Map Editor");
		this.setSize(1600, windowHeight);
		this.setFocusable(true);
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		view.addMouseListener(controller);
		this.addKeyListener(controller);
  }

  public static void main(String[] args) {
		MapEditor e = new MapEditor();
		e.run();
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