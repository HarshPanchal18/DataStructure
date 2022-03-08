#include <iostream>
#include <list>

using namespace std;

int main(void)
{
    list<int> l; // doubly linked list

    l.push_back(1);
    l.push_front(2);

    for (int i : l)
        cout << i << " ";

    cout << endl;
    l.erase(l.begin());
    cout << "\nAfter Erase: ";

    for (int i : l)
        cout << i;
    cout << "\nSize: " << l.size();
}