#include <stdio.h>
int main(void)
{
	/* A i/o program to display characters input by user.
	 * Currently has a bug that it also displays the carraige return,
	 * haven't found an easy way to flush the input stream.
	 * I'll just move and look for a solution later in the book.
	 * EDIT: Fixed.
	 */

	char ch = '0';

	while (1)
	{
		printf("Please enter a character (or q to quit).\n");
		scanf("%c", &ch);
		if (ch == 'q')
			break;
		printf("The code for %c is %d.\n\n", ch, ch);
		scanf("%c", &ch);
	}
	
	return 0;
}