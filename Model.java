import java.util.ArrayList;
import java.awt.Color;
import java.util.Iterator;

class Model {
	int camX;
	int camY;
	ArrayList<Sprite> sprites;
	ArrayList<Sprite> spritesToAdd;
	ArrayList<Sprite> spritesToRemove;
	Mario mario;
	String map;

	Model(String m) {
		map = m;
		mario = new Mario();
		sprites = new ArrayList<Sprite>();
		spritesToAdd = new ArrayList<Sprite>();
		spritesToRemove = new ArrayList<Sprite>();
		sprites.add(mario);
		makeFloor();
		loadMap(map);
	}

	public void update() {
		Iterator<Sprite> spriterator = sprites.iterator();
		while (spriterator.hasNext()) {
			Sprite s = spriterator.next();
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

  private void makeFloor() {
		Brick floorBrick = new Brick(0,900,10000,1000,new Color(15, 200, 15));
		sprites.add(floorBrick);
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
			}
		}
	}
}

