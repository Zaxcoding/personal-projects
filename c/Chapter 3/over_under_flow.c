#include <stdio.h>
int main(void)
{
	printf("This program will test int/float overflow and float underflow\n");
	
	printf("Type int is %lu bytes\n", sizeof(int));
	int i = 0x7fffffff;
	printf("Thus the largest (positive, signed) int is %#x, or %d\n", i, i);
	i++;
	printf("If I add one to that, it should go negative: %#x, %d\n", i, i);
	printf("And it does! So that's integer overflow.\n");
	
	printf("\nNext, float overflow\nType float is %lu bytes and double is"
			" %lu.\n", sizeof(float), sizeof(double));
	float f = 1234567890123456.;
	double d = 1234567890123456.;
	printf("So assigning 1234567890123456 to float and double,\n"
			"We get (float) %#.2f and (double) %.2f\n", f, d);
	f += 100.;
	d += 100.;
	printf("If I add 100 to each, check what happens:\n%#.2f, %.2f\n", f, d);
	printf("So this tells us that we can add to a float/double only when we"
			" have not\n reached the highest bound. Otherwise there is no "
			"change.\nAlso note that beyond the highest bound we get trash."
			"\nIf I try to divide by zero I get a compiler warning and also "
			"an exception that crashes the rest of the program\n");
	
	return 0;
}