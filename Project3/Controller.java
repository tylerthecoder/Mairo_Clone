import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.HashSet;

class Controller implements ActionListener, MouseListener, KeyListener {
	View view;
	Model model;
	boolean keyLeft;
	boolean keyRight;
	boolean keyUp;
	boolean keyDown;
	HashSet<Integer> keyDownBuffer;
	HashSet<Integer> keyUpBuffer;

	Controller(Model m){
		model = m;
		keyDownBuffer = new HashSet<Integer>();
		keyUpBuffer = new HashSet<Integer>();
	}

	public void keyPressed(KeyEvent e) {
    keyDownBuffer.add(e.getKeyCode());
		switch(e.getKeyCode()) {
			case KeyEvent.VK_RIGHT: keyRight = true; break;
			case KeyEvent.VK_LEFT: keyLeft = true; break;
			case KeyEvent.VK_SPACE: keyUp = true; break;
			case KeyEvent.VK_DOWN: keyDown = true; break;
			case KeyEvent.VK_S: model.saveBricks(); break; 
			case KeyEvent.VK_L: model.loadBricks(); break; 
		}
	}

	public void keyReleased(KeyEvent e) {
    keyUpBuffer.add(e.getKeyCode());
		switch(e.getKeyCode()) {
			case KeyEvent.VK_RIGHT: keyRight = false; break;
			case KeyEvent.VK_LEFT: keyLeft = false; break;
			case KeyEvent.VK_SPACE: keyUp = false; break;
			case KeyEvent.VK_DOWN: keyDown = false; break;
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	void update() {
    System.out.println("new");
    System.out.println(keyDownBuffer);
    System.out.println(keyUpBuffer);
		keyDownBuffer.forEach(key -> {
			if (key == KeyEvent.VK_SPACE) {
				model.mario.jump();
			}
      if (key == KeyEvent.VK_D) {
        int howMuchMove = model.mario.moveX(model.bricks, 1);
        model.camX += howMuchMove;
      }
      if (key == KeyEvent.VK_A) {
        int howMuchMove = model.mario.moveX(model.bricks, -1);
        model.camX += howMuchMove;
      }
      if (key == KeyEvent.VK_S) {
        model.mario.crouch();
      }
		});
    keyUpBuffer.forEach(key -> {
      keyDownBuffer.remove(key);
    });
    keyUpBuffer.clear();
	}

	public void actionPerformed(ActionEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		model.startBrick(e.getX(), e.getY());
	}

	public void mouseReleased(MouseEvent e) { 
		model.endBrick(e.getX(), e.getY());
	}

	public void mouseEntered(MouseEvent e) {    }
	public void mouseExited(MouseEvent e) {    }
	public void mouseClicked(MouseEvent e) {    }

	void setView(View v) {
		view = v;
	}
}
