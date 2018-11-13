
class Game {
  constructor () {
    this.model = new Model();
    this.view = new View(this.model);
    this.controller = new Controller(this.model);
    window.onload = this.start.bind(this);
  }

  start() {
    this.clock = window.setInterval(this.loop.bind(this), 10);
  }

  loop() {
    this.model.update();
    this.view.update();
    this.controller.update();
  }
}

const game = new Game();