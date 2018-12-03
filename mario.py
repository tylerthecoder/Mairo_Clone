import pygame
from movable import *

class Mario(Moveable):
  def __init__(self):
    self.x = 0
    self.y = 0
    self.w = 60
    self.h = 95
    self.vx = 0
    self.vy = 0
    self.isMario = True
    self.moveSpeed = 8
    self.stepSize = 2
    self.imgNumber = 0
    self.flightTime = 0
    self.sinceStep = 0
    self.images = []
    self.loadImages()

  def loadImages(self):
    for i in range(1,11):
      img = pygame.image.load("imgs/mario" + str(i) + ".png")
      self.images.append(img)

  def update(self, model):
    self.applyGravity()
    self.addVel()

    self.flightTime += 1

    self.sinceStep += abs(self.vx)
    if (self.sinceStep > 20):
      self.imgNumber = (self.imgNumber+1)%5
      self.sinceStep = 0

    if (self.vx < 0):
      self.imgNumber = self.imgNumber%5 + 5

    for sprite in model.sprites:
      if sprite == self: continue
      if self.isColliding(sprite):
        direction = self.getOut(sprite)
        sprite.spriteHit(model, self)

        if direction == 1: # on ground
          self.flightTime = 0
          self.vy = 0
        elif direction == 2:
          self.vx = 0
        elif direction == 3:
          self.vy = 0
        elif direction == 4:
          self.vx = 0

  def draw(self, screen, model):
    rect = pygame.Rect(self.x - model.camx, self.y, self.w, self.h)
    screen.blit(self.images[self.imgNumber], rect)

  def jump(self):
    if self.flightTime <= 5:
      self.vy += -1