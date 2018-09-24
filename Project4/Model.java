import java.util.ArrayList;
import java.awt.Color;
import java.util.Iterator;

class Model {
	int mouseStartX;
	int mouseStartY;
	int camX;
	int camY;
	ArrayList<Sprite> sprites;
	ArrayList<Sprite> spritesToAdd;
	ArrayList<Sprite> spritesToRemove;
	Mario mario;

	Model() {
		mario = new Mario();
		sprites = new ArrayList<Sprite>();
		spritesToAdd = new ArrayList<Sprite>();
		spritesToRemove = new ArrayList<Sprite>();
		sprites.add(mario);
		makeFloor();
		loadBricks();
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

	public void startBrick(int x, int y) {
		mouseStartX = x + camX;
		mouseStartY = y + camY;
	}

	public void endBrick(int x, int y) {
		int clickX = x + camX;
		int clickY = y + camY;
		int posX = Math.min(clickX, mouseStartX);
		int posY = Math.min(clickY, mouseStartY);
		int width =	Math.abs(clickX - mouseStartX);
		int height = Math.abs(clickY - mouseStartY);
		Brick b = new Brick(posX, posY, width, height, new Color(255,255,255));
		sprites.add(b);
	}

	public void saveBricks() {
		Json ob = Json.newObject();
		Json tempList = Json.newList();
		ob.add("sprites", tempList);
		for (int i = 0; i < sprites.size(); i++) {
			tempList.add(sprites.get(i).marshall());
		}
		ob.save("maps/map1.json");
	}

	public void loadBricks() {
		Json ob =	Json.load("maps/map1.json");
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

