#include <iostream>

int main()
{
	using namespace std;
	
	int carrots;
	
	cout << "How many carrots do you have?\n";
	cin >> carrots;
	cout << "Now you've got two more. ";
	carrots = carrots + 2;
	cout << "Now you have " << carrots << " carrots.\n";
	
	return 0;
}