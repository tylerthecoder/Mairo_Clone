from sprite import *

class Moveable(Sprite):
  def __init__(self, x, y, w, h, vx, vy):
    self.x = x
    self.y = y
    self.w = w
    self.h = h
    self.vx = vx
    self.vy = vy

  def moveBy(self, x, y):
    self.prevX = self.x
    self.prevY = self.y
    self.x += x
    self.y += y

  def addVel(self):
    self.moveBy(self.vx, self.vy)

  def applyGravity(self):
    self.vy += .1