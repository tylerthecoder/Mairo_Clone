import java.awt.Color;
import java.awt.Graphics;



class Editor {
	Model model;
	Sprite selectedObject;
  int mouseStartX;
	int mouseStartY;
	int currentMX;
	int currentMY;
	// 0: selection
	// 1: bricks
	// 2: CoinBlocks
	int tool;

  Editor(Model m) {
    model = m;
    tool = 1; // set the tool to bricks
  }

	public void startClick(int x, int y) {
		int clickX = x + model.camX;
		int clickY = y + model.camY;
		if (tool == 0) {

		} else if ( tool == 2) { //coin block
			addCoinBlock(clickX, clickY);
		}
		mouseStartX = x;
		mouseStartY = y;
	}

	public void endClick(int x, int y) {
		int clickX = x + model.camX;
		int clickY = y + model.camY;
		if (tool == 0) {
			selectObject(clickX, clickY);
		} else if (tool == 1) { // bricks tool
			addBrick(clickX, clickY);
		} else if (tool == 2) { // coin blocks

		}
	}

	public void moveMouse(int x, int y) {
		currentMX = x + model.camX;
		currentMY = y + model.camY;
	}

	public void attemptDelete() {
		if (tool == 0) {
			if (selectedObject != null) {
				model.removeSprite(selectedObject);
			}
		}
	}

	public void draw(Graphics g) {
		if (tool == 2) {

			Sprite cb = new CoinBlock(currentMX, currentMY);
			cb.draw(g, model);
		}
	}

	public void save() {
		Json ob = Json.newObject();
		Json tempList = Json.newList();
		ob.add("sprites", tempList);
		for (int i = 0; i < model.sprites.size(); i++) {
			tempList.add(model.sprites.get(i).marshall());
		}
		ob.save("maps/" + model.map + ".json");
		System.out.println("Map Saved");
	}

	public void setTool (int t) {
		tool = t;
	}

	public void selectObject (int clickX, int clickY) {
		// check to see if you are clicking a brick
		for (Sprite s : model.sprites) {
			if (s.isPointInsdie(clickX, clickY)) {
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
}