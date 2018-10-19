import java.util.ArrayList;
import java.awt.Color;
import java.util.Iterator;

enum MarioAction{
	Jump, Run, Wait, JumpNRun
}

class Model {
	int camX;
	int camY;
	ArrayList<Sprite> sprites;
	ArrayList<Sprite> spritesToAdd;
	ArrayList<Sprite> spritesToRemove;
	Mario mario;
	String map;

	Model(String _map) {
		map = _map;
		sprites = new ArrayList<Sprite>();
		spritesToAdd = new ArrayList<Sprite>();
		spritesToRemove = new ArrayList<Sprite>();
		loadMap(map);
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
			} else if (sprite instanceof Brick) {
				s = new Brick((Brick)sprite);
			} else if (sprite instanceof Coin) {
				s = new Coin((Coin)sprite);
			} else if (sprite instanceof CoinBlock) {
				s = new Coin((CoinBlock)sprite);
			}

			if (sprite != null) {
				sprites.add(s);
			}
		}
	}

	public void update() {
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

	public void loadMap(String map) {
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

	// public
	double evaluateAction(int action, int depth) {
		// Evaluate the state
		if(depth >= d) {
			return marioPos + 50 * coins - jumpCount;
		}

		// Simulate the action
		Model copy = new Model(this); // uses the copy constructor
		copy.doAction(action);
		copy.update(); // advance simulated time

		// Recurse
		if(depth % k != 0) {
				return copy.evaluateAction(action, depth + 1);
		} else {
				double best = copy.evaluateAction(run, depth + 1);
				best = Math.max(best,
					copy.evaluateAction(jump, depth + 1));
				best = Math.max(best,
					copy.evaluateAction(wait, depth + 1));
				return best;
		}
	}

	void doAction(MarioAction action) {
		if (action = MarioAction.Run) {

		}
	}

}

