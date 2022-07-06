#include <iostream>
#include <map>

using namespace std;

#define ll long long

int main(void)
{
    map<string, int> m;

    // 1. Insert
    m.insert(make_pair("Mango", 100));

    pair<string, int> p;
    p.first = "Apple";
    p.second = 120;

    m.insert(p);

    m["banana"] = 30;

    // 2. Search
    string fruit;
    cout << "\nEnter fruit for search: ";
    cin >> fruit;

    m[fruit] += 2; // updating price

    auto it = m.find(fruit);
    if (it != m.end())
        cout << "Price of " << fruit << " is " << m[fruit] << endl;
    else
        cout << "\nFruit is not found..";

    // 3.Erase
    m.erase(fruit);

    // annother way to find particular key map
    //  it stores unique keys

    if (m.count(fruit))
        cout << "Price is " << m[fruit] << "\n";

    else
        cout << "couldn't find\n";

    m["Litchie"] = 60;
    m["PineApple"] = 80;

    // Iterate all the keys
    for (auto it = m.begin(); it != m.end(); it++)
        cout << it->first << " and " << it->second << endl;

    // for each loop
    for (auto p : m)
        cout << p.first << " : " << p.second << "\n";
}
