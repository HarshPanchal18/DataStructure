#include <iostream>
#include <deque>
#include <queue>

using namespace std;

int main(void)
{
    deque<int> d;

    d.push_back(1);
    d.push_front(4);

    for (int i : d)
        cout << i << " ";

    cout << "\nPrint First Index Element: " << d.at(0);
    cout << "\nPrint Front Element: " << d.front();
    cout << "\nBack Element: " << d.back();

    cout << "\nEmpty or Not?? " << d.empty();

    cout << "\nBefore Erase: " << d.size();
    d.erase(d.begin(), d.begin() + 1);
    cout << "\nAfter Erase: " << d.size();

    for (int i : d)
        cout << endl
             << i << " ";
    cout << endl;

    /*
    d.pop_front(); // delete the first element
    d.pop_back();  // delete last element
    */

    // Queue

    queue<string> q;

    q.push("Hello");
    q.push("World");
    q.push("India");
    q.pop();
    cout << "\nFront Element: " << q.front() << endl;
    cout << "Rear Element: " << q.back() << endl;

    cout << "\nSize of the queue: " << q.size() << endl;
}
