class Model {
  constructor() {
    this.sprites = [];
    this.mario = new Mario();
    this.sprites.push(this.mario);
    this.sprites.push(new Brick(-300,sh-50,100000,50));
    this.sprites.push(new Brick(300, sh-200, 200, 200));
    this.sprites.push(new CoinBlock(700, sh-300));
  }

  update() {
    this.camx = this.mario.x - 50;
    for (const sprite of this.sprites) {
      sprite.update(this);
    }
  }
}