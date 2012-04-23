# Problem 13
# Given a huge block of numbers n,
# find the sum of 'a' b-digit numbers

def sum(n, a, b):
	string = str(n)
	sum = 0
#	print len(string)
	for y in range(0,b):			# tens place 
		for x in range(0,a):		# not exactly sure why I need to go 0,2 instead of 0,1
#			print "x:", x, "y:", y, "num:",10*x+11-y         # diagonostic
#			print string[10*x+11-y]							 # diagonostic
			sum += (10**(y))*int(string[b*x+(b-1-y)])
	#	print sum
	print sum
