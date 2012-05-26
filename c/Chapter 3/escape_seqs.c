#include <stdio.h>
int main(void)
{
	/* Trying to figure out what some of the different
	 * escape sequences do. 
	 */
	
	printf("Here comes 5 alerts! (via \\a)\a\a\a\a\a\n");
	printf("\\v is like newline, but it keeps the current position");
	printf(" of the cursor:\v like so.\n");
	printf("Here I'm typing first, \rthen I did a carraige return.\n");
	printf("Note that the above text overwrote the first text after \\r.\n");
	printf("Form feed (\\f) does this: \fmore text here.\n");
	printf("It looks a lot like \\v in that regard.\n");
	
	return 0;
}