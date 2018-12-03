from pygame import image, Rect
from movable import Moveable
from random import random

class Coin(Moveable):
  def __init__(self, cb):
    self.w = 75
    self.h = 75
    self.lifeCount = 0
    self.lifespan = 200
    self.x = cb.x
    self.y = cb.y - cb.h
    self.vx = 3 * (random() - 0.5) * (1 if random() > .5 else -1)
    self.vy = -5
    self.loadImage()

  def loadImage(self):
    self.image = image.load("imgs/coin.png")

  def update(self, model):
    self.applyGravity()
    self.addVel()
    self.lifeCount += 1
    if self.lifeCount >= self.lifespan:
      model.sprites = list(filter(lambda s: s != self, model.sprites))

  def draw(self, screen, model):
    rect = Rect(self.x - model.camx, self.y, self.w, self.h)
    screen.blit(self.image, rect)

  def spriteHit(self, model, sprite):
    self.foo = False