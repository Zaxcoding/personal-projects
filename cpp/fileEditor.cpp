#include <iostream>
#include <fstream>
#include <string>

int main()
{
	using namespace std;
	string filename;
	
	cout << "Enter name for new file: ";
	getline(cin, filename);
	
	ofstream fout(filename.c_str());
	
	string input;
	int lineNum = 0;
	
	while (input != "done")
	{
		cin.clear();
		lineNum++;
		getline(cin, input);
		if (input != "done")
			fout << lineNum << ": " << input << "\n";
	}

	fout.close();

	return 0;
}