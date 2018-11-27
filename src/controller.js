

class Controller {
  constructor(m) {
    this.model = m;
    this.keys = new Set();
    document.addEventListener("keydown", this.keyPressed.bind(this));
    document.addEventListener("keyup", this.keyReleased.bind(this));
  }

  update() {
    this.keys.delete("Alt");
    if (this.keys.size > 0) {
      //send data to server
      httpPost("http://localhost:1234/ajax_handler.html", JSON.stringify({
        action: "updatePos",
        x: Math.floor(this.model.mario.x),
        y: Math.floor(this.model.mario.y),
        id: GameId
      }), () => {})
    }

    if (this.keys.has("ArrowRight")) {
      this.model.mario.vx = 5;
    } else if (this.keys.has("ArrowLeft")) {
      this.model.mario.vx = -5;
    } else {
      this.model.mario.vx = 0;
    }

    if (this.keys.has(" ")) {
      this.model.mario.jump();
    }
  }

  keyPressed(event) {
    if (event.key === "space") {
      event.preventDefault();
    }
    this.keys.add(event.key);
  }

  keyReleased(event) {
    if (event.key === "space") {
      event.preventDefault();
    }
    this.keys.delete(event.key);
  }
}
