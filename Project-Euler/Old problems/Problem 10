def flipMultiples(testArray, multiple):
    x = 2*multiple
    while x < len(testArray):
        array[x] = 0
        x = x+multiple
 
def printPrimes(testArray):
    x = 2
    sum = 0
    while x < len(testArray):
        if testArray[x] == 1:
            sum = sum+x
            #print x
        x = x + 1
    print "Sum: ", sum
 
size = 2000000
array = [1 for x in range(size)]
subs = 2
 
while subs < size / 2:
    flipMultiples(array, subs)
    subs = subs+1
printPrimes(array)