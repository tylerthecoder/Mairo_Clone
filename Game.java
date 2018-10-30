// package Paradigms;

import javax.swing.JFrame;
import java.awt.Toolkit;

enum Mode {
	AI,
	Editor,
	Player
}

public class Game extends JFrame {
	Model model;
	Editor editor;
	Controller controller;
	View view;

	static int windowHeight = 1000;
	static int windowWidth = 1600;
	static String map = "parcore";
	static Mode mode = Mode.AI;

	public Game() {
		model = new Model(map);
		if (mode == Mode.Editor) {
			editor = new Editor(model);
			view = new View(model, editor);
			controller = new Controller(model, editor);
		} else {
			view = new View(model);
			controller = new Controller(model);
		}
		this.setTitle(mode == Mode.Editor ? "Mario Game" : "Map Editor");
		this.setSize(windowWidth, windowHeight);
		this.setFocusable(true);
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		view.addMouseListener(controller);
		view.addMouseMotionListener(controller);
		this.addKeyListener(controller);
	}

	public static void main(String[] args) {

		if (args.length > 0 && args[0].equals("Editor")) {
			System.out.println(args[0]);
			Game.map = args[1];
			Game.mode = Mode.Editor;
		}
		Game g = new Game();
		g.run();
	}

	public void run() {
		while (true) {
			controller.update();

			if (mode != Mode.Editor) {
				model.update();
			}

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
