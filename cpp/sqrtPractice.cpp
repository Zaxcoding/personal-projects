#include <iostream>
#include <cmath>

int main()
{
	using namespace std;
	
	double area;
	cout << "Enter the floor area, in sq feet, of your home: ";
	cin >> area;
	double side = sqrt(area);
	cout << "Thast's the equivalent of a square " << side << "x" << side << endl;
	cout << "Super cool!\n";
	
	return 0;
}