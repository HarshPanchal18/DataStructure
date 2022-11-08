#include <bits/stdc++.h>

using namespace std;

void _validOrNot(string &brackets)
{
    stack<char> storage;
    if (brackets[0] == '}' || brackets[0] == ')' || brackets[0] == ']')
    {
        cout << "NO";
        return;
    }
    else
    {
        for (char &i : brackets)
        {
            if (i == '(' || i == '{' || i == '[')
                storage.push(i);

            if (i == ')' || i == '}' || i == ']')
            {
                if ((storage.top() == '(' && i == ')') ||
                    (storage.top() == '{' && i == '}') ||
                    (storage.top() == '[' && i == ']'))
                    storage.pop();
                else
                    break;
            }
        }

        if (storage.empty())
            cout << "Yes";
        else
            cout << "No";
    }
}

int main(void)
{
    string brackets = "([vkdscldk])";
    cout << brackets << endl;
    _validOrNot(brackets);
}
