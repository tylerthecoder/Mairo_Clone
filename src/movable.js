class Movable extends Sprite {
  constructor (x, y, w, h, vx, vy) {
    super(x,y,w,h);
    this.vx = vx;
    this.vy = vy;
  }

  moveBy(x, y) {
    this.prevX = this.x;
    this.prevY = this.y;
    this.x += x;
    this.y += y;
  }

  addVel() {
    this.moveBy(this.vx, this.vy);
  }

  applyGravity() {
    this.vy += .1;
  }
}