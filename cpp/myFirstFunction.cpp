#include <iostream>
void zach(int);
int howMany(void);

using namespace std;				// better

int main()
{
//	using namespace std;			// these are okay
	zach(1);
	int count = howMany();
	zach(count);
	cout << "Done!" << endl;
	
	return 0;
}

void zach(int z)
{
	std::cout << "Zach is number " << z << std::endl;		// also okay
}

int howMany(void)
{
//	using namespace std;			// okay
	cout << "How many?\n";
	int count;
	cin >> count;
	return count;
}