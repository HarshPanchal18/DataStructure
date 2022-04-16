#include <iostream>
#include <vector>

using namespace std;

int main(void)
{
    int n;
    cout << "\nEnter a number: ";
    cin >> n;

    vector<int> digit;

    digit.push_back(1);

    for (int j = 1; j < n + 1; j++)
    {
        int x = 0, y = 0;

        for (int i = digit.size() - 1; i >= 0; i--)
        {
            x = j * digit[i] + y;
            digit[i] = x % 10;
            y = x / 10;
        }
        while (y != 0)
        {
            digit.insert(digit.begin(), y % 10);
            y /= 10;
        }
    }

    cout << digit.capacity() << endl;
    cout << digit.size() << endl;

    cout << "The Factorial is: ";
    for (size_t i = 0; i < digit.size(); i++)
        cout << digit[i];
}
