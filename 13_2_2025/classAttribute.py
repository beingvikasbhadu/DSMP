class A:
   a=10
   @classmethod
   def fun(cls):
      cls.b=20
   def fun1(slef):
      #calling class variable through the instance method
      print("a:",A.a,"b:",A.b)
   
    

print("Hello World!")
#to add 'b' into the class variables
A.fun()
print(A.__dict__)
a1=A()
a1.fun1()