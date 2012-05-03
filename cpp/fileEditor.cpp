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
	
	bool done = false;
	string input;
	int lineNum = 0;
	//s
	
	while (!done)
	{
		cin.clear();
		lineNum++;
		getline(cin, input);
		fout << lineNum << ": " << input << "\n";
		if (input == "done")
			done = true;
	}

	fout.close();

	return 0;
}