# Prime finder
# Finds and displays all primes < n
# This is brute force- very slow. Change to sieve of erasthonese

def prime(n):
    list = []
    x = a = 1
    while len(list) < n:
        a = x
        x += 2
        while a >= 1:
            if ( a == 1 ):
                list.append(x)
                break
            if ( x % a == 0 or x % 5 == 0 or x % 2 == 0):
                break
            else:
                a -= 1
            
    print list