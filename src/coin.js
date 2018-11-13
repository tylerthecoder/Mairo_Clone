class Coin extends Movable {
  constructor (cb) {
    super(0,0,75,75)
    this.lifeCount = 0;
    this.lifespan = 200;
    this.x = cb.x;
    this.y = cb.y - this.h;
    this.vx = 20 * (Math.random() - 0.5) * (Math.random() > .5) ? -1 : 1;
    this.vy = -5;
    this.image = new Image();
    this.image.src = "imgs/coin.png";
  }

  update(model) {
    this.applyGravity();
    this.addVel();
    this.lifeCount++;
    if (this.lifeCount >= this.lifespan) {
      model.sprites = model.sprites.filter(s => s != this);
    }
  }

  draw(paint, model) {
    paint.drawImage(this.image, this.x - model.camx, this.y);
  }

}