import pygame

class View:
  screen_size = (800,600)
  def __init__(self, model):
    self.model = model
    self.screen_size = (800,600)
    self.screen = pygame.display.set_mode(self.screen_size, 32)

  def update(self):
    self.screen.fill([0, 200, 100])
    for sprite in self.model.sprites:
      sprite.draw(self.screen, self.model)
    pygame.display.flip()