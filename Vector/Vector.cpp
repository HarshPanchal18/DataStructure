#include <iostream>
#include <vector>

using namespace std;

int main(void)
{
    vector<int> v;
    cout << "Current Capacity: " << v.capacity();

    vector<int> v1(5, 1); // Create a vector capacity with capacity of 5 elements and initialize with 1.
    v1.push_back(7);
    cout << endl;
    for (int i : v1)
        cout << i << " ";

    v.push_back(6);
    cout << "\nCurrent Capacity: " << v.capacity();

    v.push_back(6);
    cout << "\nCurrent Capacity: " << v.capacity();

    v.push_back(9);
    cout << "\nCurrent Capacity: " << v.capacity();

    v.push_back(7);
    cout << "\nCurrent Capacity: " << v.capacity();

    v.push_back(8);
    cout << "\nCurrent Capacity: " << v.capacity();

    cout << "\nVector Size: " << v.size();

    cout << "\nElement at 2nd Index: " << v.at(2); // Vector is also starts from 0

    cout << "\nFront Element: " << v.front();
    cout << "\nLast Element: " << v.back();

    cout << "\nBefore pop:" << endl;
    for (int i : v)
        cout << i << " ";
    cout << endl;

    v.pop_back();

    cout << "\nAfter pop:" << endl;
    for (int i : v)
        cout << i << " ";
    cout << endl;

    vector<int> last(v1);
    cout << endl;
    for (int i : last)
        cout << i << " "; // copy vector v1 to the vector last

    /* cout << "\nBefore Clear the size: " << v.size();
    v.clear(); // Clears only vector data, not capacity.
    cout << "\nAfter Clear the size: " << v.size();*/
}