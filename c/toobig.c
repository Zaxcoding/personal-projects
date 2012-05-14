#include <stdio.h>
int main(void)
{
	printf("This program was to examine what happens you exceed");
	printf(" the maximum values of int and unsigned int. I included");
	printf(" the binary values as well so it's easier to see.\n\n");
	
	int i = 2147483647;
	unsigned int j = 4294967295;
	
	printf("%d %d %d\n%#x %#x %#x\n\n", i, i+1, i+2, i, i+1, i+2);
	printf("%u %u %u\n%#x %#x %#x\n\n", j, j+1, j+2, j, j+1, j+2);
	
	return 0;
}