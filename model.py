from mario import *
from brick import Brick
from coinblock import CoinBlock


class Model():
	def __init__(self):
		self.sprites = []
		self.mario = Mario()
		self.sprites.append(self.mario)
		self.sprites.append(
			Brick(-100,550,1000,100)
		)
		self.sprites.append(
			Brick(0,400,200,200)
		)
		self.sprites.append(
			Brick(400,400,200,200)
		)
		self.sprites.append(
			CoinBlock(400, 200)
		)
		self.camx = 0

	def update(self):
		self.camx = self.mario.x - 100
		for sprite in self.sprites:
			sprite.update(self)
