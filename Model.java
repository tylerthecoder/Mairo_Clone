import java.util.ArrayList;
import java.awt.Color;
import java.util.Iterator;
// import Paradigms.sprites.*;

enum MarioAction{
	Forward,
	Jump,
	Wait,
	Backwards
}

class Model {
	int camX;
	int camY;
	int count;
	ArrayList<Sprite> sprites;
	ArrayList<Sprite> spritesToAdd;
	ArrayList<Sprite> spritesToRemove;
	Mario mario;
	String map;

	int d = 6;
	int k = 6;

	Model(String _map) {
		sprites = new ArrayList<Sprite>();
		spritesToAdd = new ArrayList<Sprite>();
		spritesToRemove = new ArrayList<Sprite>();
		map = _map;
		loadMap();
	}

	Model(Model m) {
		camX = m.camX;
		camY = m.camY;
		sprites = new ArrayList<Sprite>();
		spritesToAdd = new ArrayList<Sprite>();
		spritesToRemove = new ArrayList<Sprite>();
		for (Sprite sprite : m.sprites) {
			Sprite s = null;
			if (sprite instanceof Mario) {
				s = new Mario((Mario)sprite);
				mario = (Mario)s; // I swear it is mario
			} else if (sprite instanceof Brick) {
				s = new Brick((Brick)sprite);
			} else if (sprite instanceof Coin) {
				s = new Coin((Coin)sprite);
			} else if (sprite instanceof CoinBlock) {
				s = new CoinBlock((CoinBlock)sprite);
			}

			if (sprite != null) {
				sprites.add(s);
			}
		}
	}

	public void print() {

		System.out.println(sprites.size());
		for (Sprite s : sprites) {
			System.out.println(s.x);
		}
	}

	public void update() {
		// System.out.println(++count);
		for (Sprite s : sprites) {
			s.update(this);
		}

		for (Sprite s : spritesToAdd) {
			sprites.add(s);
		}
		spritesToAdd.clear();

		for (Sprite s : spritesToRemove) {
			sprites.remove(s);
		}
		spritesToRemove.clear();

		camX = mario.x - 100;
	}

	public void addSprite(Sprite s) {
		spritesToAdd.add(s);
	}

	public void removeSprite(Sprite s) {
		spritesToRemove.add(s);
	}

	public void loadMap() {
		Json ob =	Json.load("maps/" + map + ".json");
		Json jsonSprites = ob.get("sprites");
		for (int i = 0; i < jsonSprites.size(); i++) {
			Json s = jsonSprites.get(i);
			String type = s.getString("type");
			if (type.equals("brick")) {
				sprites.add(new Brick(s));
			} else if (type.equals("coinBlock")) {
				sprites.add(new CoinBlock(s));
			} else if (type.equals("mario")) {
				mario = new Mario(s);
				sprites.add(mario);
			} else if (type.equals("platform")) {
				sprites.add(new Platform(s));
			}
		}
	}

	public double evaluateAction(MarioAction action, int depth) {
		double score = 0.0;

		score = 3*(mario.x - mario.prevX) + 5000 * mario.coinBlockCount /* + 6000 * mario.coinsCollected */ - (mario.jumpCount + mario.waitTime);
		if(depth >= d) {
			if (mario.dead) return 0;
			return score;
		}

		score /= (depth + 1);

		// Simulate the action
		Model copy = new Model(this); // uses the copy constructor

		for (int i = 0; i < k; i++) {
			copy.doAction(action);
			copy.update();
		}

		double best = 0.0;
		for (MarioAction mAction: MarioAction.values()) {
			best = Math.max(best, copy.evaluateAction(mAction, depth + 1));
		}
		if (best == 0) return 0;
		return best + score;
	}

	void doAction(MarioAction action) {
		if (action == MarioAction.Forward) {
			mario.moveX(1);
		} else if (action == MarioAction.Backwards) {
			mario.moveX(-1);
		} else if (action == MarioAction.Wait) {
			mario.waitPlz();
		} else if (action == MarioAction.Jump) {
			mario.jump();
		}
	}

}

