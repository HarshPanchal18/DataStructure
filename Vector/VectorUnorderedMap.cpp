#include <iostream>
#include <vector>
#include <unordered_map>

using namespace std;

int main(void)
{
    cin.tie(NULL);
    unordered_map<string, vector<string>> phBook;

    phBook["Rahul"].push_back("2574");
    phBook["Rahul"].push_back("2575");
    phBook["Rahul"].push_back("2576");
    phBook["Kajal"].push_back("2584");
    phBook["Kajal"].push_back("2585");
    phBook["Kajal"].push_back("2586");

    for (auto p : phBook)
    {
        cout << "Contact Name: " << p.first << " -> ";
        for (string s : p.second)
            cout << s << ", ";
        cout << endl;
    }

    string name;
    cout << "\nEnter contact name: ";
    cin >> name;

    if (phBook.count(name) == 0)
        cout << "\nNot Found";
    else
        for (string s : phBook[name])
            cout << s << endl;
}
