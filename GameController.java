import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;


public class GameController extends Controller {
  boolean isAI = true;

  GameController(Model m) {
    super(m);
  }

  public void update () {
    if (isAI) {
      double scoreJump = model.evaluateAction(MarioAction.Jump, 0);
      double scoreRun = model.evaluateAction(MarioAction.Run, 0);
      double scoreWait = model.evaluateAction(MarioAction.Wait, 0);

      System.out.println("Next:");
      System.out.println(scoreJump);
      System.out.println(scoreRun);
      System.out.println(scoreWait);

      // Do the best one
      if(scoreJump > scoreRun && scoreJump > scoreWait) {
        model.doAction(MarioAction.Jump);
      } else if(scoreRun > scoreWait) {
        model.doAction(MarioAction.Run);
      } else {
        model.doAction(MarioAction.Wait);
      }
    } else {
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
          System.out.println("WHAt");
          copy.print();
          System.exit(1);
        }
      };

      model.mario.moveX(speed * mult);

      keyUpBuffer.forEach(key -> {
        keyDownBuffer.remove(key);
      });
      keyUpBuffer.clear();
    }
  }


}