#include <iostream>
#include <map>

using namespace std;

/*
A standard container made up of (key,value) pairs, which can be retrieved based on a key, in logarithmic time.
--- data store with key value
--- key is different two all
--- same key doesn't point multiple values
*/

int main(void)
{
    map<int, string> m;

    m[4] = "Hp";
    m[2] = "Dell";
    m[7] = "Lenovo";

    m.insert({0, "Microsoft"});

    for (auto i : m)
        cout << i.first << " " << i.second << endl; // always sorted, first = key; second = value

    // cout << "\nCount: " << m.count(7); // is count(key) available or not

    m.erase(4); // remove key

    for (auto i : m)
        cout << i.first << " " << i.second << endl;

    auto it = m.find(2);

    for (auto i = it; i != m.end(); i++)
        cout << (*i).first << endl;
}
