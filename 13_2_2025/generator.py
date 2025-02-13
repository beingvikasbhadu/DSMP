# generator are special function that gives generator object
# generator object is also iterator object 
# we uses yield keyword in  it 
# yield is same as return keyword but yield does not end the function it only pauses the function and remember the current state

def fun():
   count=1 
   while True:
    yield count
    count+=1

# generator object is created
gen=fun()
print(next(gen))
print(next(gen))
print(next(gen))
