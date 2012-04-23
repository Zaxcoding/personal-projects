# Problem 2
# Displays the terms of the Fibonnaci Sequence < n
# and also the sum of the even-valued terms.

def fib(n):
	a, b = 1, 2
	sum = 0
	print "Terms in the Fibonnaci Sequence less than " + str(n) + ":\n"
	while ( a and b ) < n:
		print str(a) + " " + str(b) + " ",
		if a % 2 == 0: 	sum += a
		if b % 2 == 0:	sum += b
		a, b = b+a, b+b+a	
	print "\nSum of the even-valued terms:" , sum