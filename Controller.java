import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.HashSet;

public abstract class Controller implements ActionListener, MouseListener, KeyListener {
	View view;
	Model model;
	Editor editor;
	HashSet<Integer> keyDownBuffer;
	HashSet<Integer> keyUpBuffer;

	Controller(Model m){
		model = m;
		keyDownBuffer = new HashSet<Integer>();
		keyUpBuffer = new HashSet<Integer>();
	}

	abstract void update();

	public void keyPressed(KeyEvent e) {
    keyDownBuffer.add(e.getKeyCode());
	}

	public void keyReleased(KeyEvent e) {
    keyUpBuffer.add(e.getKeyCode());
	}

	public void keyTyped(KeyEvent e) {}
	public void actionPerformed(ActionEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
}
