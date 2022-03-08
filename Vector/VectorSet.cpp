#include <iostream>
#include <set>

using namespace std;

int main(void)
{
  set<int> s;

  s.insert(7);
  s.insert(2);
  s.insert(4);
  s.insert(5);
  s.insert(9);

  for (int i : s)
    cout << i << endl;

  /*  s.erase(s.begin());

    cout << "\nAfter removing:\n";
    for (int i : s)
        cout << i << endl;
*/
  set<int>::iterator it = s.begin();

  /*  s.erase(it); // erases the itTH element of the set.
    cout << "\nAfter Erasing:\n";
    for (int i : s)
        cout << i << endl;
*/
  cout << "\nPresent: " << s.count(4); // returns 1 if the element is present or not.

  set<int>::iterator itr = s.find(2);
  cout << endl;
  // cout << "\nvalue present at: " << *itr << endl;

  for (auto it = itr; it != s.end(); it++) // print elements after itr or s.find()
    cout << *it << endl;
}