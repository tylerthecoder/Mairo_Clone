import pygame
from sprite import Sprite
from coin import Coin

class CoinBlock(Sprite):
  def __init__(self,x, y):
    self.x = x
    self.y = y
    self.w = 89
    self.h = 83
    self.images = []
    self.cooldown = 12
    self.spitCount = 0
    self.loadImages()

  def loadImages(self):
    for i in range(1,3):
      imgStr = "imgs/block" + str(i) + ".png"
      self.images.append(pygame.image.load(imgStr))

  def update(self, model):
    if self.cooldown > 0:
      self.cooldown -= 1

  def draw(self, screen, model):
    imgNum = 1 if self.spitCount >= 5 else 0
    rect = pygame.Rect(self.x - model.camx, self.y, self.w, self.h)
    screen.blit(self.images[imgNum], rect)

  def spriteHit(self, model, sprite):
    if sprite.isMario and sprite.y > self.y + self.h - 2 and self.spitCount < 5 and self.cooldown <= 0:
      c = Coin(self)
      model.sprites.append(c)
      self.cooldown = 12
      self.spitCount += 1
