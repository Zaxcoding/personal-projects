# Problem 1
# Sums the natural number multiples of a, b less than n
def multiples(a, b, n):
	list = []
	for x in a, b, a*b:
		print "\nSum of the multiples of", x, "less than", n, ":"
		num = int((n-.1)/x)							# number of multiples of xless than n
		floatnum = float(num)						# that num in float so we can divide odd by 2
		sum = int(x*(floatnum+1)*(floatnum/2))		# back to int so we can display it nicely
		print sum
		list.append(sum)
	print "Final answer =", list[0], "+", list[1], "-", list[2], "=", list[0]+list[1]-list[2]