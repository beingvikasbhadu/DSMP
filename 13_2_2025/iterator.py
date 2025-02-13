it=iter([1,2,3,4])
while True:
   try:
    print(next(it))
   except StopIteration as e:
    break
print("out of loop!")