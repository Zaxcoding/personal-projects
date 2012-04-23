# Problem 4
# Finds the largest palindrome made from the product of two n-digit numbers.

def Palin(n):							# n is the digits of the two numbers
	import math							# could easily become for any n, m digt numbers
	a = low = int(math.pow(10,n-1))		# just set two lows, highs. Have low1 < a < high1
	high = int(math.pow(10, n))			# 								 low2 < a < high2
	big = 0
	while a < high:
		a += 1
		b = low
		while b < high:
			b += 1
			doit = 1
			product = str(a*b)
			length = len(product)
			for x in range(0, length, 1):
				if product[x] != product[-(x+1)]:
					doit = 0
			if doit == 1 and a*b > big:
					big = a*b
					biga = a
					bigb = b
#					print "A:", a, "B:", b, "Palindrome:", big		for live updates

	print "A:", biga, "B:", bigb, "Palindrome:", big
