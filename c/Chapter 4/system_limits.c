#include <stdio.h>
#include <limits.h>
#include <float.h>
int main(void)
{
	printf("This program just lists some of the system limits for types.\n");
	printf("\nBiggest int: %d\n", INT_MAX);
	printf("Biggest long int: %ld\n", LONG_MAX);
	printf("Biggest long long int: %lld\n", LLONG_MAX);
	printf("One byte is %d bits\n", CHAR_BIT);
	printf("Largest double: %e\n", DBL_MAX);
	printf("Smallest normal float: %e\n", FLT_MIN);
	printf("Float precision: %d digits\n", FLT_DIG);
	printf("Float epsilon: %e\n", FLT_EPSILON);
	
	return 0;
}