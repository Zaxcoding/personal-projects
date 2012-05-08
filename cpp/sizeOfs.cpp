#include <iostream>
#include <climits>

int main()
{
	using namespace std;
	
	int nInt = INT_MAX;
	short nShort = SHRT_MAX;
	long nLong = LONG_MAX;		// these are defined in climits
	
	cout << "\n\n"
		 << "int is " << sizeof nInt << " bytes.\n"
		 << "short is " << sizeof nShort << " bytes.\n"
		 << "long is " << sizeof nLong << " bytes.\n\n";
	
	cout << "Maximum values:\n"
		 << "int: " << nInt << endl
		 << "short: " << nShort << endl
		 << "long: " << nLong << "\n\n";
		 
	cout << "Minimum int value = " << INT_MIN << endl
		 << "Bits per byte = " << CHAR_BIT << endl;
		 
	return 0;
}