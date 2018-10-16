import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class GameController extends Controller {

  GameController(Model m) {
    super(m);
  }

  public void update () {
    int speed = 0;
    int mult = 1;
    for (int key : keyDownBuffer) {
			if (key == KeyEvent.VK_SPACE) {
				model.mario.jump();
      }

      if (key == KeyEvent.VK_D) {
        speed = 1;
      } else if (key == KeyEvent.VK_A) {
        speed = -1;
      }
      if (key == KeyEvent.VK_SHIFT) {
        mult = 2;
      }

      if (key == KeyEvent.VK_S) {
        model.mario.crouch();
      }
		};

    model.mario.moveX(speed * mult);

    keyUpBuffer.forEach(key -> {
      keyDownBuffer.remove(key);
    });
    keyUpBuffer.clear();
  }


}