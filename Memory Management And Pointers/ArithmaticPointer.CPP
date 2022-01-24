#include <iostream>
#include <conio.h>
using namespace std;
void main()
{
	int num[] = {56, 78, 90, 43, 18};

	int i, *ptr;

	// clrscr();

	cout << "The array values are:" << endl;
	for (i = 0; i < 5; i++)
		cout << num[i] << endl;

	ptr = num;
	cout << "\nvalue of ptr     " << *ptr;

	ptr++;
	cout << "\nValue of ptr++   " << *ptr;

	ptr--;
	cout << "\nValue of ptr--   " << *ptr;

	ptr += 2;
	cout << "\nValue of ptr/2   " << *ptr;

	ptr -= 1;
	cout << "\nValue of ptr-1   " << *ptr;

	ptr += 3;
	cout << "\nValue of ptr+3   " << *ptr;

	ptr -= 2;
	cout << "\nValue of ptr-2   " << *ptr;

	getch();
}