#include<stdio.h>
int main(void)
{
	printf("Give me an ASCII code value, and I'll tell you what it is\n"
		"(or enter q to quit)\n\n>> ");
	_Bool done = 0;
	char ch;
	while (!done)
	{
		scanf("%c", &ch);
		if (ch == 'q')
			done = 1;
		if (ch != 10)		// because I still can't flush the enter key
		{
			printf("Your num was %d, which is %c", ch, ch);
			printf("\n>> ");
		}
	}
	return 0;
}