import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import javax.swing.JPanel;


public class Controller extends JPanel implements ActionListener, MouseListener, MouseMotionListener, KeyListener {
  public Editor editor;
	public Model model;
	public HashSet<Integer> keyDownBuffer;
	public HashSet<Integer> keyUpBuffer;

  Controller(Model m) {
    model = m;
		keyDownBuffer = new HashSet<Integer>();
		keyUpBuffer = new HashSet<Integer>();
  }

  Controller(Model m, Editor e) {
    this(m);
    editor = e;
  }

  public void update () {
    if (Game.mode == Mode.AI) {
      MarioAction action = MarioAction.Wait; // defaults to moving forward
      double best = 0.0;
      // System.out.println("Next");
      for (MarioAction mAction: MarioAction.values()) {
        double score = model.evaluateAction(mAction, 0);
        // System.out.print(mAction);
        // System.out.print(": ");
        // System.out.println(score);
        if (score > best) {
          best = score;
          action = mAction;
        }
      }
      model.doAction(action);

    } else if(Game.mode == Mode.Player) {
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
          Model copy = new Model(model);
          model.print();
          copy.print();
          System.exit(1);
        }
      };

      model.mario.moveX(speed * mult);

      keyUpBuffer.forEach(key -> {
        keyDownBuffer.remove(key);
      });
      keyUpBuffer.clear();
    } else if(Game.mode == Mode.Editor) {
      for (int key : keyDownBuffer) {
        if (key == KeyEvent.VK_0) editor.setTool(Tool.Selector);
        if (key == KeyEvent.VK_1) editor.setTool(Tool.Brick);
        if (key == KeyEvent.VK_2) editor.setTool(Tool.Floor);
        if (key == KeyEvent.VK_3) editor.setTool(Tool.CoinBlock);
        if (key == KeyEvent.VK_9) editor.setTool(Tool.Mario);
        if (key == KeyEvent.VK_RIGHT) model.camX += 32;
        if (key == KeyEvent.VK_LEFT) model.camX -= 32;
        if (key == KeyEvent.VK_D) editor.attemptDelete();
        if (key == KeyEvent.VK_S) editor.save();
      };

      keyUpBuffer.forEach(key -> {
        keyDownBuffer.remove(key);
      });
      keyUpBuffer.clear();
    }
  }

  public void mousePressed(MouseEvent e) {
		if (editor != null) editor.startClick(e.getX(), e.getY());
	}

	public void mouseMoved(MouseEvent e) {
		if (editor != null) editor.moveMouse(e.getX(), e.getY());
	}

	public void mouseDragged(MouseEvent e) {
		if (editor != null) editor.moveMouse(e.getX(), e.getY());
	}

	public void mouseReleased(MouseEvent e) {
		if (editor != null) editor.endClick(e.getX(), e.getY());
	}

  public void keyPressed(KeyEvent e) {
    keyDownBuffer.add(e.getKeyCode());
	}

	public void keyReleased(KeyEvent e) {
    keyUpBuffer.add(e.getKeyCode());
	}

	public void keyTyped(KeyEvent e) {}
	public void actionPerformed(ActionEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}


}