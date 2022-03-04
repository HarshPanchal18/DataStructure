#include <iostream>
#include <stack>

using namespace std;

int main(void)
{
    stack<string> s;

    s.push("Love");
    s.push("Hello");
    s.push("World");
    s.pop();

    cout << "Top of stack: " << s.top();
    cout << "\nSize of stack: " << s.size();
    cout << "\nEmpty or Not: " << s.empty();
}