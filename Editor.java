import java.awt.Color;
import java.awt.Graphics;

enum tools {
	Selector, Brick, CoinBlock, Mario
}

class Editor {
	Model model;
	Sprite selectedObject;
  int mouseStartX;
	int mouseStartY;
	int currentMX;
	int currentMY;
	boolean isMouseDown;
	// 0: selection
	// 1: bricksmouseStartX = x;
	// 2: CoinBlocks
	int tool;

  Editor(Model m) {
    model = m;
    tool = 1; // set the tool to bricks
  }

	public void startClick(int x, int y) {
		int clickX = x + model.camX;
		int clickY = y + model.camY;
		isMouseDown = true;
		mouseStartX = clickX;
		mouseStartY = clickY;
		if (tool == 0) { //selection tool
			selectObject(clickX, clickY);
		}else if ( tool == 2) { //coin block
			addCoinBlock(clickX, clickY);
		}else if (tool == 9) {
			setMario(clickX, clickY);
		}
	}

	public void endClick(int x, int y) {
		isMouseDown = false;
		int clickX = x + model.camX;
		int clickY = y + model.camY;
		if (tool == 1) { // bricks tool
			addBrick(clickX, clickY);
		}
	}

	public void moveMouse(int x, int y) {
		currentMX = x + model.camX;
		currentMY = y + model.camY;
	}

	public void attemptDelete() {
		if (tool == 0) {
			if (selectedObject != null) {
				model.sprites.remove(selectedObject);
			}
		}
	}

	public void draw(Graphics g) {
		if (tool == 1 && isMouseDown) {
			int posX = Math.min(currentMX, mouseStartX);
			int posY = Math.min(currentMY, mouseStartY);
			int width =	Math.abs(currentMX - mouseStartX);
			int height = Math.abs(currentMY - mouseStartY);
			Sprite b = new Brick(posX, posY, width, height, Color.WHITE);
			b.draw(g, model);
		} else if (tool == 2) {
			Sprite cb = new CoinBlock(currentMX, currentMY);
			cb.draw(g, model);
		} else if (tool == 9) {
			Sprite m = new Mario(currentMX, currentMY);
			m.draw(g, model);
		}
	}

	public void save() {
		Json ob = Json.newObject();
		Json tempList = Json.newList();
		ob.add("sprites", tempList);
		for (Sprite s : model.sprites) {
			tempList.add(s.marshall());
		}
		ob.save("maps/" + Game.map + ".json");
		System.out.println("Map Saved");
	}

	public void setTool (int t) {
		tool = t;
	}

	public void selectObject (int clickX, int clickY) {
		// check to see if you are clicking a brick
		for (Sprite s : model.sprites) {
			if (s.isPointInsdie(clickX, clickY)) {
				System.out.println("Ye[");
				selectedObject = s;
				break;
			}
		}
	}

	public void addBrick (int clickX, int clickY) {
		int posX = Math.min(clickX, mouseStartX);
		int posY = Math.min(clickY, mouseStartY);
		int width =	Math.abs(clickX - mouseStartX);
		int height = Math.abs(clickY - mouseStartY);

		Brick b = new Brick(posX, posY, width, height, new Color(255,255,255));
		model.sprites.add(b);
	}

	public void addCoinBlock (int clickX, int clickY) {
		CoinBlock cb = new CoinBlock(clickX, clickY);
		model.sprites.add(cb);
	}

	public void setMario (int clickX, int clickY) {
		if (model.mario != null) {
			model.mario.x = clickX;
			model.mario.y = clickY;
		} else {
			Mario m = new Mario(clickX, clickY);
			model.mario = m;
			model.sprites.add(m);
		}
	}
}