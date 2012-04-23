# Problem 15
# Finds the number of possible
# ways to get from the top left
# to the bottom right of a nxn grid.

def grid(n):
	list=[0]
	print list
	z=1
	a=1
	while z <= n:
		list.append(1)
		z += 1
	print list
	while a <= n:
		z += 1
		list.append(1)
		while z <= (a+1)*n+a*1:	
	#		print list[z-(a*n+1)], list[z-1]
			list.append(list[z-(n+1)]+list[z-1])
			z += 1
		a+=1
	print list[z-1]
