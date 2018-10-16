import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class EditorController extends Controller {
	public Editor editor;

	EditorController(Model m, Editor e) {
		super(m);
		editor = e;
	}

	public void mousePressed(MouseEvent e) {
		editor.startClick(e.getX(), e.getY());
	}

	public void mouseMoved(MouseEvent e) {
		editor.moveMouse(e.getX(), e.getY());
	}

	public void mouseReleased(MouseEvent e) {
		editor.endClick(e.getX(), e.getY());
	}

	void update() {
    for (int key : keyDownBuffer) {
			if (key == KeyEvent.VK_0) { // Selection tool
				editor.setTool(0);
			} else if (key == KeyEvent.VK_1){
				editor.setTool(1);
			} else if (key == KeyEvent.VK_2){
				editor.setTool(2);
			}

      if (key == KeyEvent.VK_D) {
				editor.attemptDelete();
			}

			if (key == KeyEvent.VK_A) {

			}

      if (key == KeyEvent.VK_SHIFT) {

      }

      if (key == KeyEvent.VK_S) {
				editor.save();
      }
		};

    keyUpBuffer.forEach(key -> {
      keyDownBuffer.remove(key);
    });
    keyUpBuffer.clear();
	}
}
