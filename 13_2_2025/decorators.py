#decorators: it decorates the input function with extra functionality; o/p would be function 
def myDecorator(func):
  def wrapper():
    print("before decoration!")
    func()
    print("after decoration!")
  return wrapper

@myDecorator
def fun():
  print("main function!")
fun()