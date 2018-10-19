import javax.swing.JFrame;
import java.awt.Toolkit;

public class MapEditor extends JFrame {
  Model model;
	EditorController controller;
  View view;
  Editor editor;
  static int windowHeight;

  public MapEditor (String map) {
    model = new Model(map);
		editor = new Editor(model);
		view = new View(model, editor);
		controller = new EditorController(model, editor);
		windowHeight = 1000;
		this.setTitle("Map Editor");
		this.setSize(1600, windowHeight);
		this.setFocusable(true);
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		view.addMouseListener(controller);
		view.addMouseMotionListener(controller);
		this.addKeyListener(controller);
  }

  public static void main(String[] args) {
		System.out.println(args[0]);
		MapEditor e = new MapEditor(args[0]);
		e.run();
	}

	public void run() {
		while (true) {
			controller.update();
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