class Mario extends Movable {
  constructor(view) {
    super(0,sh - 200,60,95,0,0);
    this.isMario = true;
    this.moveSpeed = 8;
    this.stepSize = 2;
    this.image;
    this.imgNumber = 0;
    this.flightTime = 0;
    this.sinceStep = 0;
    this.jumpCount = 0;
    this.images = []
    this.loadImages();
  }

  loadImages() {
    for (let i = 1; i <= 10; i++) {
      const img = new Image();
      img.src = "imgs/mario" + i + ".png";
      this.images.push(img);
    }
  }

  update(model) {
    this.applyGravity();
    this.addVel();

    this.flightTime++;

    this.sinceStep += Math.abs(this.vx);
    if (this.sinceStep > 20) {
      this.imgNumber = (this.imgNumber+1)%5;
      this.sinceStep = 0;
    }

    if (this.vx < 0) {
      this.imgNumber = this.imgNumber%5 + 5;
    }


    for (const s of model.sprites) {
      if (s == this) continue;
      if (this.isColliding(s)) {
        const dir = this.getOut(s);
				s.spriteHit(model, this);

				if (dir == 1 ) { //on ground
					this.flightTime = 0;
					this.vy = 0;
				} else if (dir == 2) {
					this.vx = 0;
				} else if (dir == 3) {
					this.vy = 0;
				} else if (dir == 4) {
					this.vx = 0;
				}
      }
    }
  }

  draw(paint, model) {
    paint.drawImage(this.images[this.imgNumber], this.x - model.camx, this.y)
  }

  jump() {
    if (this.flightTime <= 5) {
      this.vy += -1;
    }
  }
}