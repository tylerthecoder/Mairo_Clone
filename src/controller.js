class Controller {
  constructor(m) {
    this.model = m;
    this.keys = new Set();
    document.addEventListener("keydown", this.keyPressed.bind(this));
    document.addEventListener("keyup", this.keyReleased.bind(this));
  }

  update () {
      if (this.keys.has("ArrowRight")) {
        this.model.mario.vx = 5;
      } else if (this.keys.has("ArrowLeft")) {
        this.model.mario.vx = -5;
      } else {
        this.model.mario.vx = 0;
      }

      if (this.keys.has(" ")) {
        this.model.mario.jump()
      }
  }

  keyPressed(event) {
    this.keys.add(event.key);
	}

	keyReleased(event) {
    this.keys.delete(event.key);
	}
}