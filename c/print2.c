#include <stdio.h>
int main(void)
{
	/* This was supposed to show that you can cause errors through
	 * incorrect use of %d, %hd, %lld, etc when not appicable, but
	 * it appears that this is now a compilation error.
	 */
	
	unsigned int un = 3000000000;
	short end = 200;
	long big = 65537;
	long long verybig = 12345678908642;
	
	printf("un = %u and not %d\n", un, un);
	printf("end = %hd and %d\n", end, end);
	printf("big = %ld\n", big);
	printf("verybig = %lld in hex %#llx\n", verybig, verybig);
	
	return 0;
}