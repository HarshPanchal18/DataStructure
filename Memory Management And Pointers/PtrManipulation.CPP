#include <iostream>
#include <conio.h>

using namespace std;

int main()
{
	int a = 10, *ptr;

	ptr = &a;

	cout << "The value of a is  " << a;
	cout << endl;

	*ptr /= 2;

	cout << "The value of a is   " << *ptr;

	getch();
	return 0;
}