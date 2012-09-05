#include "Foo.h"
#include <stdio.h>

Foo::Foo()		// definition for Foo's constructor
{
	printf("I am a constructor that calls myMethod\n");
	int a = myMethod(5,2);
	printf("a = %d\n",a);
}

Foo::~Foo()
{
	printf("Destructor here.\n");
}

int Foo::MyMethod(int a, int b)
{
	return a+b;
}