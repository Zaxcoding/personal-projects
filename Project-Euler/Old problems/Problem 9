# Problem 9
# A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,
#							a^(2) + b^(2) = c^(2)
#There exists exactly one Pythagorean triplet for which a + b + c = 1000.
#Find the product abc.

def Triple(n):
	a = b = c = 0
	print "The Pythagorean triplets such that a + b + c =", n, "\n"
	while a < n/3:
		a += 1
		b = a+1
		c = n-a-b
		while b < c:
			if a*a + b*b == c*c:
				print "A:", a, "B:", b, "C:", c
			b += 1
			c -= 1
	