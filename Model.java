import java.util.ArrayList;

class Model {
	int mouseStartX;
	int mouseStartY;
	int camX;
	int camY;
	ArrayList<Brick> bricks;
	Model() {
		bricks = new ArrayList<Brick>(); 	
	}

	public void update() {

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
		Brick b = new Brick(posX, posY, width, height); 
		bricks.add(b);			
	}
	
	public void saveBricks() {
		Json ob = Json.newObject();
		Json tempList = Json.newList();
		ob.add("bricks", tempList);
		for (int i = 0; i < bricks.size(); i++) {
			tempList.add(bricks.get(i).json());	
		}
		ob.save("bricks.json");
	}

	public void loadBricks() {
		Json ob =	Json.load("bricks.json"); 
		bricks = new ArrayList<Brick>();
		Json jsonBricks = ob.get("bricks");
		for (int i = 0; i < jsonBricks.size(); i++) {
			bricks.add(new Brick(jsonBricks.get(i)));
		}
	}

}

