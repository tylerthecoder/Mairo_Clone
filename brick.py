from sprite import Sprite
import pygame

class Brick(Sprite):
  def __init__(self, x, y, w, h):
    self.x = x
    self.y = y
    self.w = w
    self.h = h

  def draw(self, screen, model):
    x = self.x - model.camx
    width = self.w
    if x < 0:
      width = self.w + x
      x = 0

    r = pygame.Rect(x, self.y, width, self.h)
    screen.fill((255, 0, 0), r)

  def spriteHit(self, model, sprite):
    self.hitting = False

  def update(self, model):
    self.updating = False
