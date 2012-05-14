#include <stdio.h>
int main(void)
{
	int x = 152;
	
	printf("This program was to teach that '%%#' can be used");
	printf(" before o, x to display the 0 or 0x prefixes to oct and hex.\n"); 
	
	printf("\ndec = %d -- oct = %o -- hex = %x\n", x, x, x);
	printf("dec = %d -- oct = %#o -- hex = %#x\n", x, x, x);
	
	return 0;
}