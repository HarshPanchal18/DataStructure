#include <iostream>
#include <conio.h>
#include <string.h>

using namespace std;

int main(void)
{
	char str[] = "test";
	int len = strlen(str);

	for (int i = 0; i < len; i++)
		cout << str[i] << i[str] << *(str + i) << *(i + str);
	cout << endl;

	int Length = len / 2;
	len--;

	for (int i = 0; i < Length; i++)
	{
		str[i] = str[i] + str[len - i];
		str[len - i] = str[i] - str[len - i];
		str[i] = str[i] - str[len - i];
	}
	cout << "Reverse   " << str;
	// getch();
}