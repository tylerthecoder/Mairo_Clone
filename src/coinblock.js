class CoinBlock extends Sprite {
  constructor(x, y) {
    super(x, y, 89, 83);
    this.images = [];
    this.cooldown = 12;
    this.spitCount = 0;
    for (let i = 0; i < 2; i++) {
      this.images[i] = new Image();
      this.images[i].src = "imgs/block" + (i+1) + ".png";
    }
  }

  update() {
    if (this.cooldown > 0) {
      this.cooldown--;
    }
  }

  draw(paint, model) {
    const imgNum = this.spitCount >= 5 ? 1 : 0;

    paint.drawImage(this.images[imgNum], this.x - model.camx, this.y);
  }

  spriteHit(model, s) {
    console.log("hit");
    if (s.isMario && s.y > this.y + this.h-2 && this.spitCount < 5 && this.cooldown <= 0) {
      const c = new Coin(this);
      model.sprites.push(c);
      this.cooldown = 12;
      this.spitCount++;
    }
  }
}