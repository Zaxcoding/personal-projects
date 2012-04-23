# Problem 14
# Displays the starting number and collatz
# of starting numbers < highest

def Collatz(highest):
	print "Starting number : Collatz number\n of starting numbers <", highest
	table = range(1, highest, 2)
	table[0] = 2
	i = 0
	collatz = range(2, 3)
	working = 0
	sequence = []
	big=0
	while (collatz[-1] < highest):
			sequence = []
			working = collatz[-1]
			while( working > 1):
				if working in sequence:
					sequence.append(working)
					break
				else:
					sequence.append(working)
				if working % 2 ==0:
					working *= .5
				else:
					working = 3*working + 1
			if len(sequence) > big:
				big=len(sequence)
				print collatz[-1], ":  ",  big		# to list each greater collatz, and that starting number
	#			print sequence						# to list the collatz sequence of that starting number
				
	#begin toying with it
	#		for x in table:
	#			print x
	#			if x % collatz[-1] == 0:
	#				table.remove(x)
	#				print table
	#end toying
			i += 1
			collatz.append(table[i])
	#		print "i=", i
	#		if collatz[-1] > highest/2:
	#			print table