class Sprite:
  def __init__(self, x, y, w, h):
    self.x = x
    self.y = y
    self.w = w
    self.h = h
    self.prevX = x
    self.prevY = y

  def isPointInside(self, x, y):
    return (
      self.x + self.w > x and
      self.x          < x and
      self.y + self.h > y and
      self.y          < y
    )

  def isColliding(self, s):
    return (
      self.x + self.w > s.x       and
      self.x          < s.x + s.w and
      self.y + self.h > s.y       and
      self.y          < s.y + s.h
    )

  def getOut(self, s):
    if self.y + self.h >= s.y and (not (self.prevY + self.h > s.y)): # from top
      self.y = s.y - self.h
      return 1
    elif self.y <= s.y + s.h and (not (self.prevY < s.y + s.h)): # from bottom
      self.y = s.y + s.h
      return 3
    elif self.x + self.w >= s.x and not (self.prevX + self.w > s.x): # from left
      self.x = s.x - self.w
      return 4
    elif self.x <= s.x + s.w and not (self.prevX < s.x + s.w): # from right
      self.x = s.x + s.w
      return 2
    return 0