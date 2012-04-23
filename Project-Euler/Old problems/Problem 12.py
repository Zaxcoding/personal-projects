# Problem 12
# What is the value of the first
# triangle number to have over
# five hundred divisors?

#def factors(a):				# finds the factors of a
#	x = num = 0				# and returns how many to 
#	while x <= a:
#		x += 1
#		if a % x == 0:
#			num += 1
#	return num

def triangle(n):
	bestnum = best = 0
	b = a = 1
	while bestnum <= n:
		a += 1
		b += a
		###
		
		x = num = 0				# and returns how many to 
		while x <= b:
			x += 1
			if b % x == 0:
				num += 1
		###
		
	#	print num
		if num > bestnum:
			bestnum = num
			best = b
			print "A:", a, "B:", b, "Bestnum:", bestnum, "Best:", best
	