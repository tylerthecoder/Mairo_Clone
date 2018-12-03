import pygame
import time
from model import Model
from controller import Controller
from view import View
from pygame.locals import *
from time import sleep


class Game:
  def __init__(self):
    pygame.init()
    self.model = Model()
    self.view = View(self.model)
    self.controller = Controller(self.model)

  def run(self):
    while self.controller.keep_going:
      self.controller.update()
      self.model.update()
      self.view.update()
      sleep(0.01)


g = Game()
g.run()