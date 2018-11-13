class Brick extends Sprite {
  constructor(x, y, w, h) {
    super(x,y,w,h);
  }


  draw(paint, model) {
    paint.fillRect(this.x - model.camx, this.y, this.w, this.h);
  }

  update() {

  }

}
