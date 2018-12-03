import pygame
from pygame.locals import *

class Controller:
  def __init__(self, model):
    self.model = model
    self.keep_going = True

  def update(self):
    for event in pygame.event.get():
      if event.type == QUIT:
        self.keep_going = False
      elif event.type == KEYDOWN:
        if event.key == K_ESCAPE:
          self.keep_going = False
    keys = pygame.key.get_pressed()
    # if keys[K_LEFT]:
    #   self.model.mario.x += 5
    # elif keys[K_RIGHT]:
    #   self.model.mario.x -= 5
    # elif keys[K_UP]:
    #   self.model.mario.y -= 5
    # elif keys[K_DOWN]:
    #   self.model.mario.y += 5
    if keys[K_LEFT]:
      self.model.mario.vx = -5
    elif keys[K_RIGHT]:
      self.model.mario.vx = 5
    else:
      self.model.mario.vx = 0

    if keys[K_SPACE]:
      self.model.mario.jump()