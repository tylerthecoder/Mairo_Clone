class Sprite {
  constructor(x, y, w, h) {
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
    this.prevX = x;
    this.prevY = y;
  }

  isPointInside(x, y) {
    return (
      this.x + this.w > x &&
      this.x          < x &&
      this.y + this.h > y &&
      this.y          < y
    )
  }

  isColliding(s) {
    return (
      this.x + this.w > s.x       &&
      this.x          < s.x + s.w &&
      this.y + this.h > s.y       &&
      this.y          < s.y + s.h
    )
  }

  getOut(s) {
    if (this.y + this.h >= s.y && !(this.prevY + this.h > s.y)) { //from top
      this.y = s.y - this.h;
      return 1;
    } else if (this.y <= s.y + s.h && !(this.prevY < s.y + s.h)) { // from bottom
      this.y = s.y + s.h;
      return 3;
    } else if (this.x + this.w >= s.x && !(this.prevX + this.w > s.x)) { // from left
      this.x = s.x - this.w;
      return 4;
    } else if (this.x <= s.x + s.w && !(this.prevX < s.x + s.w)) { // from right
      this.x = s.x + s.w;
      return 2;
    }
    return 0;
  }

  draw() {}
  spriteHit() {}
}