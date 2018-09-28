import java.awt.Color;


class Editor {
  Model model;
  int mouseStartX;
	int mouseStartY;
	// 0: selection
	// 1: bricks
	// 2: CoinBlocks
	int tool;

  Editor(Model m) {
    model = m;
    tool = 1; // set the tool to bricks
  }

	public void startClick(int x, int y) {
		if (tool == 0) {
			// check to see if you are clicking a brick
			for (Sprite s : model.sprites) {

			}
		}
		mouseStartX = x;
		mouseStartY = y;
	}

	public void endClick(int x, int y) {
		int clickX = x + model.camX;
		int clickY = y + model.camY;

		if (tool == 1) { // bricks tool
			int posX = Math.min(clickX, mouseStartX);
			int posY = Math.min(clickY, mouseStartY);
			int width =	Math.abs(clickX - mouseStartX);
			int height = Math.abs(clickY - mouseStartY);

			Brick b = new Brick(posX, posY, width, height, new Color(255,255,255));
			model.sprites.add(b);
		} else if (tool == 2) {

		}
	}
}