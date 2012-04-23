# Problem 16
# Displays n to the yth power
# Then sums the digits
# Key was to learn to go from
# Strings to ints and back

def Power(n,y):
	print n, "to the " + str(y) + "th power is:"
	ans=n**y							# this is how to do powers in Python
	print ans
	print "\nThe sum of the digits is:"
	string=str(ans)
	sum = 0
	for x in string:					# use this to automatically iterate
		sum += int(x)					# through the string/ number's digits
	print sum
	
# A much shorter version is:
# def Power(m,y):
# sum = 0
# for x in str(n**y):
# sum += int(x)
# print sum