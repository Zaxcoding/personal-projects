# Problem 3
# Finds the prime factorization of n
# works for any n, natural number

def prime(n):
	factorization = "Prime Factorization: 1"
	x = 2
	while x <= int(n**.5):
		while( n % x ) == 0:
			n /= x
			factorization+= "*" + str(x)
		x += 1		
	
	print factorization