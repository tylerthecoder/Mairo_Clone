const sw = window.innerWidth;
const sh = window.innerHeight;

class View {
  constructor (model) {
    this.model = model;
    this.canvas = document.getElementById("myCanvas");
    this.canvas.width = sw;
    this.canvas.height = sh;
    this.paint = this.canvas.getContext("2d");
    this.backimg = new Image();
    this.backimg.src = "imgs/background.png";
  }

  update() {
    this.paint.clearRect(0, 0, sw, sh);
    this.paint.drawImage(this.backimg, -this.model.camx/3, 0);
    for (const sprite of this.model.sprites) {
      sprite.draw(this.paint, this.model);
    }
  }
}