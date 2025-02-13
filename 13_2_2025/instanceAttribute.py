class A:
   #instance method
   def __init__(self):
   #instance variable 
        self.a=100

a1=A()
print(a1.a)
#instance variable
a1.b=200

print(a1.__dict__)